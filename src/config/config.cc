#include "config/config.hh"

#include <fstream>
#include <iostream>

#include "error/parsing-error.hh"
#include "misc/json.hh"

namespace http
{
    /*
    ** Check the given port value is in the range 0 to 65535 as defined
    ** in the RFC 6335
    */
    static bool valid_port_value(int port)
    {
        // Binding to port 0 is equivalent to binding to a random unused port
        return port >= 0 && port <= 65535;
    }

    /*
    ** fill a VHostConfig from a json object
    */
    static void from_json(const json& j, VHostConfig& config)
    {
        auto port = j.at("port");
        port.get_to(config.port);
        if (!valid_port_value(port))
            throw ParsingError("Invalid port value");
        j.at("ip").get_to(config.ip);
        j.at("server_name").get_to(config.server_name);

        config.default_file = j.value("default_file", "index.html");

        config.is_default_vhost = j.value("is_default_vhost", false);

        if (config.default_file == ".." || config.default_file == "."
            || config.default_file.find("/") != std::string::npos)
            throw ParsingError(
                "default file has to define a file and not a path ");

        config.root = j.value("root", ".");

        config.cud_files = j.value("cud_files", false);
    }

    /*
    ** fill a ServerConfig from a json object
    */
    static void from_json(const json& j, ServerConfig& config)
    {
        j.at("vhosts").get_to(config.vhosts);
        if (config.vhosts.empty())
            throw ParsingError("vhosts filed should be an array containing at "
                               "least one vhost");
    }

    /*
    ** Parse config file located at `path`. If test, displays errors
    ** and exit. if not, return an object built with the given config.
    **
    ** Specs about config file:
    **   - Unknown directives are ignored
    **   - It is case sensitive
    **   - A `vhosts` array is mandatory. It's an array of objects.
    ** Each of this object represent a virtual host. Each object must define at
    ** least their port and ip address.
    **   - `port` must be a JSON number (RFC 8259)
    **   - Even if there is only one vhost, `vhosts` must be an array
    */
    ServerConfig parse_configuration(const std::string& path)
    {
        std::ifstream ifs(path, std::ifstream::in);
        if (!ifs.is_open())
            throw ParsingError("Could not open file: " + path);

        json j;
        ifs >> j;
        ServerConfig config = j;

        return config;
    }
} // namespace http

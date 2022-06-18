#include "readiness.hh"

#include <cstring>

namespace misc
{
    void announce_spider_readiness(char* argv_0)
    {
        std::strcpy(argv_0, "[READY]");
    }
} // namespace misc

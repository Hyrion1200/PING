#include "misc/fd.hh"

#include "misc/unistd.hh"

namespace misc
{
    FileDescriptor::~FileDescriptor()
    {
        if (fd_ != -1)
            sys::close(fd_);
    }

    FileDescriptor& FileDescriptor::operator=(FileDescriptor&& fileDescriptor)
    {
        if (fd_ != -1)
            sys::close(fd_);
        fd_ = std::exchange(fileDescriptor.fd_, -1);
        return *this;
    }

    FileDescriptor::operator int() const&
    {
        return this->fd_;
    }

} // namespace misc

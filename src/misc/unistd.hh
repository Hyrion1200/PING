/**
 * \file misc/unistd.hh
 * \brief unistd.h syscall wrappers.
 */

#pragma once

#include <signal.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#include "misc/fd.hh"
#include "misc/sys-wrapper.hh"

namespace sys
{
    /**
     * \brief close(2).
     */
    inline auto close = make_wrapper<void>(::close);

    /**
     * \brief write(2).
     */
    inline auto write = make_wrapper<void>(::write);

    /**
     * \brief fork(2).
     */
    inline auto fork = make_wrapper<pid_t>(::fork);

    /**
     * \brief fstat(2).
     */
    inline auto fstat = make_wrapper<int>(::fstat);

    /**
     * \brief kill(2).
     */
    inline auto kill = make_wrapper<void>(::kill);

    static int open_wrapper(const char* path, int flags)
    {
        return ::open(path, flags);
    }
    /**
     * \brief open(2).
     *
     * Since open is a variadic syscall we need to call a helper with a
     * fixed number of parameters to create a wrapper out of it.
     */
    inline auto open = make_wrapper<misc::FileDescriptor>(open_wrapper);

    static int open_perm_wrapper(const char* path, int flags, mode_t mode)
    {
        return ::open(path, flags, mode);
    }
    /**
     * \brief open(2) with permission flags.
     *
     * Since open is a variadic syscall we need to call a helper with a
     * fixed number of parameters to create a wrapper out of it.
     */
    inline auto open_perm =
        make_wrapper<misc::FileDescriptor>(open_perm_wrapper);

    /**
     * \brief wait(2).
     */
    inline auto waitpid = make_wrapper<void>(::waitpid);
} // namespace sys

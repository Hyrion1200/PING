add_library(
  misc STATIC
  fd.cc
)

target_link_libraries(
  misc
  PRIVATE addrinfo
          openssl
          readiness
)

add_subdirectory(readiness)
add_subdirectory(addrinfo)
add_subdirectory(openssl)

add_library(
  socket STATIC
  default-socket.cc
)

target_link_libraries(
  socket
  PRIVATE misc
)

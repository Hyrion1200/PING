add_library(
  config STATIC
  config.cc
)

target_link_libraries(
  config
  PRIVATE misc
)

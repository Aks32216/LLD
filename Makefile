CXX      := clang++
CXXFLAGS := -std=c++17 -Wall -Wextra -g
BINDIR   := /tmp/cpp_builds

# Run a specific file:  make run FILE=01_OOP/basics/main.cpp
run:
	@if [ -z "$(FILE)" ]; then \
		echo "Usage: make run FILE=<path/to/file.cpp>"; \
		exit 1; \
	fi
	@mkdir -p $(BINDIR)
	@BIN=$(BINDIR)/$(basename $(notdir $(FILE))); \
	echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"; \
	echo "  Compiling: $(FILE)"; \
	echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"; \
	$(CXX) $(CXXFLAGS) $(FILE) -o $$BIN && \
	echo "  Running..." && \
	echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" && \
	$$BIN

# List all .cpp files in the project
list:
	@find . -name "*.cpp" | sort

.PHONY: run list

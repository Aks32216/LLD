#!/usr/bin/env bash
# Usage: ./run.sh <path/to/file.cpp> [compiler args...]
# Example: ./run.sh 01_OOP/basics/main.cpp

set -e

if [ -z "$1" ]; then
    echo "Usage: ./run.sh <path/to/file.cpp> [extra compiler flags]"
    echo "Example: ./run.sh 01_OOP/basics/main.cpp"
    exit 1
fi

FILE="$1"
shift

if [ ! -f "$FILE" ]; then
    echo "Error: File '$FILE' not found."
    exit 1
fi

BINARY="/tmp/cpp_run_$(basename "${FILE%.cpp}")"

echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "  Compiling: $FILE"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

clang++ -std=c++17 -Wall -Wextra -g "$FILE" "$@" -o "$BINARY"

echo "  Running..."
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

"$BINARY"

echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "  Done. Exit code: $?"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

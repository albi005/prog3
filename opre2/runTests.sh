#!/usr/bin/env bash

# set -e

cp src/main/java/Main.java .

javac Main.java

if [ ! -f inputs/*01.txt ]; then
  echo "Missing test files."
  exit
fi

if [ ! -f outputs/*01.txt ]; then
  echo "Missing result files."
  exit
fi

pass=0
fail=0

for t in `cd inputs; ls *.txt`; do
  cat inputs/$t | java Main > out.$$
  diff="$(diff -q outputs/$t out.$$ )"
  if [ "$diff" == "" ]; then
    pass=$((pass+1))
  else
    fail=$((fail+1))
    echo "Test inputs/$t failed. Input:"
    cat inputs/$t
    echo "Diff:"
    diff --color outputs/$t out.$$
    echo
  fi
done

\rm out.$$
rm Main.java
rm *.class

echo "Results: $pass passed $fail failed tests."

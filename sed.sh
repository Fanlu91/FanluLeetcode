#!/bin/bash

find . -type f | xargs -n1 sed -i "" "s'Fanlu Hai'Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode'g"

find . -type f | xargs -n1 sed -i "" 's/2018-0/2019-0/g'
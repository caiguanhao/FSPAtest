Demo Android project using:

* [Android-PullToRefresh](https://github.com/chrisbanes/Android-PullToRefresh)
* [Android-ViewPagerIndicator](https://github.com/JakeWharton/Android-ViewPagerIndicator)

HOW TO USE
==========

Command Line
------------

Get source code:

    git clone git://github.com/caiguanhao/FSPAtest.git && cd FSPAtest
    git submodule init
    git submodule update

Build project:

    android update project -p .
    android update project -p submodules/PullToRefresh/library/
    android update project -p submodules/ViewPagerIndicator/library/
    ant clean debug

Start device and Install:

    android list avd | grep -o 'Name: .*' | sed 's/Name: //' | sed -n 1p | xargs emulator -avd &
    adb install bin/*-debug.apk

Eclipse
-------

PullToRefresh and ViewPagerIndicator have the same project name 'project'. In Eclipse you can't import projects with the same name. So don't import all libraries at once.

Import PullToRefresh first and right click the library, choose Refactor > Rename..., and then change it to PullToRefreshLibrary (or other name).

You may also import the library of ViewPagerIndicator and rename it as ViewPagerIndicatorLibrary (or other name).

If everything including API targets are set up but errors still exist, try to re-open the projects and libraries or restart Eclipse.
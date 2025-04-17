# Backtracking-4

## Problem1: Optimal Placement of Buildings in a grid

Given a grid with w as width, h as height. Each cell of the grid represents a potential building lot and we will be adding "n" buildings inside this grid. The goal is for the furthest of all lots to be as near as possible to a building. Given an input n, which is the number of buildings to be placed in the lot, determine the building placement to minimize the distance the most distant empty lot is from the building. Movement is restricted to horizontal and vertical i.e. diagonal movement is not required.


For example, w=4, h=4 and n=3. An optimal grid placement sets any lot within two unit distance of the building. The answer for this case is 2.


"0" indicates optimal building placement and in this case the maximal value of all shortest distances to the closest building for each cell is "2".

```
1 0 1 2
2 1 2 1
1 0 1 0
2 1 2 1
```

## Problem2: Word List Brace Expansion 
https://leetcode.com/problems/brace-expansion/



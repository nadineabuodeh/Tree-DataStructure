# Floorplan Slicing Tree

## Overview

This repository contains a Java implementation of a floorplan slicing tree, a data structure that represents a floorplan as a rooted binary tree. Each node in the tree represents a rectangular space, and the tree structure is used to naturally describe the layout of the floorplan.

## Implementation

The floorplan slicing tree is implemented in the `FloorPlanning` class, which extends `AbstractBinaryTree`. The tree is composed of nodes, each storing information about the dimensions (height and width) and content (element) of the corresponding space in the floorplan.

## Features

- **Tree Decomposition**: The class provides methods to decompose a node horizontally or vertically, effectively slicing the floorplan and creating new spaces.

- **Query Functions**: The implementation includes methods to retrieve the height and width of a given position in the tree.

- **String Representation**: A `toString` method is available to generate a string representation of the floorplan slicing tree.


## Acknowledgments
- This implementation is based on the concepts of floorplan slicing trees.
Feel free to explore and enhance the functionality of the floorplan slicing tree implementation as needed for your specific use case!

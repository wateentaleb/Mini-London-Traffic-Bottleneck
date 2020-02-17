# Mini London's Traffic Bottleneck

## Overview
Being a city planner that is in charge of vital construction projects such as **traffic intersection maintenance**. The Mayor of the City of Mini-London suspects the most important intersection to maintain is the one for which the most number of vehicles cross, and would like you to determine this intersection.

For simplicity, we will call this the **bottleneck intersection**. All the intersections of Mini-London are numbered 0 to 63, an undirected graph representing the road structure of Mini-London is given below for your reference. There are many factors that go into determining how busy an intersection is and what constitutes a traffic bottleneck, we make a simplifying assumption by considering only the total frequency of vehicles that travel through an intersection. **For this project, a Java program is written that determines a bottleneck intersection for a simulated traffic flow of vehicles.**

<img width="844" alt="map " src="https://user-images.githubusercontent.com/16707828/74684748-a8fa2280-519a-11ea-8a1b-d2f44c00e1f1.png">

he traffic flow is simulated via a path-finding algorithm that performs a depth-first search in the graph. Upon finding a path, code provided to you will update the data storing the number of crossings at an intersection, this data is stored in nodes of an AVL Tree. You will implement:
+ an AVL Tree;
+ a path-finding algorithm (depth-first search).

Traffic was simulated by using The official website of the City of London Traffic Volume webpage, the link can be accessed here: https://www.london.ca/residents/Roads-Transportation/traffic-management/Pages/Traffic-Volumes.aspx


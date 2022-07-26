package com.zombie.map;

import com.zombie.map.Tile;

import java.util.ArrayList;

public class PathFinder {
    final ArrayList<Tile> tileMap;

    public PathFinder(ArrayList<Tile> tileMap) {
        this.tileMap = tileMap;
    }

    public int getDistance(Tile start, Tile end) {
        return Math.abs(start.y - end.y) + Math.abs(start.x - end.x);
    }

    public ArrayList<Tile> findPath(Tile start, Tile end) {
        int mapSize = tileMap.size();
        ArrayList<Tile> openList = new ArrayList<>(50);
        ArrayList<Tile> explored = new ArrayList<>();
        openList.add(start);
        Tile currentTile = null;
        end.parent = null;

        while (openList.size() > 0) {
            currentTile = lowestFScore(openList);
            ArrayList<Tile> neighbours = currentTile.getNeighbors(currentTile);
            for (Tile neighbour : neighbours) {
                if (!explored.contains(neighbour) && neighbour.isWalkable() && !neighbour.hasUnit()) {
                    int gScore = currentTile.gScore + getDistance(neighbour, currentTile) + neighbour.getTimeUnitCost();
                    if (!openList.contains(neighbour)) {
                        openList.add(neighbour);
                        neighbour.hScore = getDistance(neighbour, end);
                        neighbour.gScore = gScore;
                        neighbour.parent = currentTile;
                    } else if (gScore < neighbour.gScore) {
                        neighbour.gScore = gScore;
                        neighbour.parent = currentTile;
                    }

                    neighbour.fScore = neighbour.gScore + neighbour.hScore;
                }
            }

            openList.remove(currentTile);

            if (!explored.contains(currentTile)) {
                explored.add(currentTile);
                currentTile.explored = true;
            }

            if (currentTile == end) {
                break;
            }
        }

        if (end.parent == null) {
            return null;
        }

        return reConstructPath(start, currentTile, mapSize);
    }

    public ArrayList<Tile> reConstructPath(Tile start, Tile end, int mapSize) {

        ArrayList<Tile> path = new ArrayList<>();
        Tile currentNode = end;

        while (currentNode != start) {
            if (currentNode == null) {
                return null;
            }

            if (path.size() > mapSize) {
                return null;
            }

            path.add(currentNode);
            currentNode = currentNode.parent;
        }

        return path;
    }

    public Tile lowestFScore(ArrayList<Tile> openList) {

        Tile lowest = openList.get(0);

        for (Tile t : openList) {
            if (t.fScore < lowest.fScore) {
                lowest = t;
            } else if (t.fScore == lowest.fScore) {
                if (t.hScore < lowest.hScore) {
                    lowest = t;
                }
            }
        }

        return lowest;
    }
}



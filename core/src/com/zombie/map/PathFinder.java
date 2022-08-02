package com.zombie.map;

import com.zombie.entities.Tile;

import java.util.ArrayList;
import java.util.Collections;

public class PathFinder {
    final int BASE_WALK_COST = 4;
    final ArrayList<Tile> tileMap;

    public PathFinder(ArrayList<Tile> tileMap) {
        this.tileMap = tileMap;
    }

    public int getDistance(Tile start, Tile end) {
        return Math.abs(start.getY() - end.getY()) + Math.abs(start.getX() - end.getX());
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
                    int gScore = currentTile.gScore + getDistance(neighbour, currentTile) + neighbour.getMovementCost();
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
        ArrayList<Tile> reversePath = new ArrayList<>();
        Tile currentNode = end;

        while (currentNode != start) {
            if (currentNode == null) {
                return null;
            }

            if (reversePath.size() > mapSize) {
                return null;
            }

            reversePath.add(currentNode);
            currentNode = currentNode.parent;
        }

        Collections.reverse(reversePath);
        ArrayList<Tile> path = new ArrayList<>();
        int availableTimeUnits = start.getUnitOnTile().getAvailableTimeUnits();
        int cost = 0;
        Tile previousTile = start;
        for (Tile tile : reversePath) {
            int distance = getDistance(previousTile, tile);
            int movementCost = BASE_WALK_COST + tile.getMovementCost();
            if (distance > 1) {
                movementCost *= 1.5;
            }

            if (cost + movementCost <= availableTimeUnits) {
                cost += movementCost;
                path.add(tile);
            } else {
                break;
            }

            previousTile = tile;
        }

        if (path != null && path.size() > 0) {
            path.get(0).setPathCost(cost);
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



/*
package ru.leti;

import ru.leti.wise.task.graph.model.Edge;
import ru.leti.wise.task.graph.model.Graph;
import ru.leti.wise.task.graph.model.Vertex;
import ru.leti.wise.task.graph.model.Color;
import ru.leti.wise.task.plugin.graph.GraphProperty;

import java.util.*;


public class RamseyGraph implements GraphProperty {
    private final int r;
    private final int s;


    public RamseyGraph(int r, int s) {
        this.r = r;
        this.s = s;
    }

    @Override
    public boolean run(Graph graph) {
        List<Vertex> vertices = graph.getVertexList();
        List<Edge> edges = graph.getEdgeList();

        // Индексируем рёбра от 0 до m-1
        int m = edges.size();

        // Для быстрого доступа: map пар (u,v) -> индекс ребра
        Map<Long, Integer> edgeIndex = new HashMap<>();
        for (int i = 0; i < m; i++) {
            Edge e = edges.get(i);
            int u = e.getSource();
            int v = e.getTarget();
            long key = encodePair(u, v);
            edgeIndex.put(key, i);
            // Для неориентированного: тоже обратная пара
            edgeIndex.put(encodePair(v, u), i);
        }

        int n = vertices.size();

        // Перебираем все 2^m раскрасок рёбер в {RED, BLUE}
        int totalColorings = 1 << m;
        for (int mask = 0; mask < totalColorings; mask++) {
            // Для каждой раскраски проверяем, есть ли RED-клику размера r
            // или BLUE-клику размера s
            if (!hasMonochromeClique(vertices, edgeIndex, mask, Color.RED, r)
                    && !hasMonochromeClique(vertices, edgeIndex, mask, Color.BLUE, s)) {
                // Нашлась раскраска без ни RED-K_r, ни BLUE-K_s
                return false;
            }
        }
        // Во всех раскрасках требуемая монохромная клика есть
        return true;
    }

    //@Override
   // public String getName() {
    //    return String.format("Ramsey(%d,%d)", r, s);
    //}

    // Проверяем, что при данной раскраске (mask) существует клика заданного цвета size
    private boolean hasMonochromeClique(List<Vertex> verts,
                                        Map<Long, Integer> edgeIndex,
                                        int mask,
                                        Color color,
                                        int size) {
        int n = verts.size();
        // Рекурсивно/итеративно генерируем все комбинации вершин C(n, size)
        int[] combo = new int[size];
        // Инициализация первой комбинации [0,1,2,...,size-1]
        for (int i = 0; i < size; i++) {
            combo[i] = i;
        }
        while (combo[0] <= n - size) {
            // Проверяем, образуют ли эти вершины клику нужного цвета
            if (isCliqueOfColor(verts, combo, edgeIndex, mask, color)) {
                return true;
            }
            // Генерация следующей комбинации
            int t = size - 1;
            while (t >= 0 && combo[t] == n - size + t) {
                t--;
            }
            if (t < 0) break;
            combo[t]++;
            for (int i = t + 1; i < size; i++) {
                combo[i] = combo[i - 1] + 1;
            }
        }
        return false;
    }

    // Проверка: все пары в combo соединены ребром заданного цвета
    private boolean isCliqueOfColor(List<Vertex> verts,
                                    int[] combo,
                                    Map<Long, Integer> edgeIndex,
                                    int mask,
                                    Color color) {
        for (int i = 0; i < combo.length; i++) {
            for (int j = i + 1; j < combo.length; j++) {
                int u = verts.get(combo[i]).getId();
                int v = verts.get(combo[j]).getId();
                Integer idx = edgeIndex.get(encodePair(u, v));
                if (idx == null) {
                    // Ребро отсутствует в графе => не клика
                    return false;
                }
                boolean isColoredRed = ((mask >> idx) & 1) == 1;
                if (color == Color.RED && !isColoredRed) return false;
                if (color == Color.BLUE && isColoredRed) return false;
            }
        }
        return true;
    }

    // Кодируем упорядоченную пару (u,v) в long
    private long encodePair(int u, int v) {
        return (((long) u) << 32) | (v & 0xffffffffL);
    }
}

*/


package ru.leti;

import ru.leti.wise.task.graph.model.Graph;
import ru.leti.wise.task.graph.model.Vertex;
import ru.leti.wise.task.graph.model.Edge;
import ru.leti.wise.task.graph.model.Color;

import java.util.*;

public class RamseyGraph {
    private final int r;
    private final int s;

    /**
     * @param r размер красной клики RED
     * @param s размер синей клики BLUE
     */
    public RamseyGraph(int r, int s) {
        this.r = r;
        this.s = s;
    }

    /**
     * Проверяет: в данном графе (с уже раскрашенными рёбрами)
     * есть ли монохромная RED-клика размера r
     * или BLUE-клика размера s.
     */
    public boolean run(Graph graph) {
        List<Vertex> verts = graph.getVertexList();
        int n = verts.size();

        // Собираем множество соседей по цвету
        Map<Integer, Set<Integer>> redAdj = new HashMap<>();
        Map<Integer, Set<Integer>> blueAdj = new HashMap<>();
        for (Vertex v : verts) {
            redAdj.put(v.getId(), new HashSet<>());
            blueAdj.put(v.getId(), new HashSet<>());
        }
        for (Edge e : graph.getEdgeList()) {
            if (e.getColor() == Color.RED) {
                redAdj.get(e.getSource()).add(e.getTarget());
                redAdj.get(e.getTarget()).add(e.getSource());
            } else if (e.getColor() == Color.BLUE) {
                blueAdj.get(e.getSource()).add(e.getTarget());
                blueAdj.get(e.getTarget()).add(e.getSource());
            }
            // GRAY — пропускаем
        }

        // Проверяем каждый цветовой граф на наличие клики нужного размера
        return hasClique(redAdj, getVertexIds(verts), r)
                || hasClique(blueAdj, getVertexIds(verts), s);
    }

    private List<Integer> getVertexIds(List<Vertex> verts) {
        List<Integer> ids = new ArrayList<>(verts.size());
        for (Vertex v : verts) ids.add(v.getId());
        return ids;
    }

    /**
     * Проверка: в графе adj (map: вершина -> её соседи)
     * есть ли клика размера k.
     */
    private boolean hasClique(Map<Integer,Set<Integer>> adj, List<Integer> ids, int k) {
        if (k <= 1) return !ids.isEmpty();
        int n = ids.size();
        // generate combinations C(n,k)
        int[] combo = new int[k];
        for (int i = 0; i < k; i++) combo[i] = i;
        while (combo[0] <= n - k) {
            if (isClique(adj, ids, combo)) return true;
            // next combo
            int t = k - 1;
            while (t >= 0 && combo[t] == n - k + t) t--;
            if (t < 0) break;
            combo[t]++;
            for (int i = t+1; i < k; i++) combo[i] = combo[i-1] + 1;
        }
        return false;
    }

    private boolean isClique(Map<Integer,Set<Integer>> adj, List<Integer> ids, int[] combo) {
        for (int i = 0; i < combo.length; i++) {
            for (int j = i+1; j < combo.length; j++) {
                int u = ids.get(combo[i]);
                int v = ids.get(combo[j]);
                // если нет ребра между u и v в этом подграфе — не клика
                if (!adj.get(u).contains(v)) return false;
            }
        }
        return true;
    }
}


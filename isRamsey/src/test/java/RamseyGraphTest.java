import ru.leti.RamseyGraph;
import ru.leti.wise.task.graph.util.FileLoader;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RamseyGraphTest {
    @Test
    public void GraphTest1() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(2, 3);
        var graph1 = FileLoader.loadGraphFromJson("src/test/resources/k6_r2_s3_True.json");
        assertThat(ramseyGraph.run(graph1)).isFalse();
    }


    @Test
    public void GraphTest2() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(2, 3);
        var graph2 = FileLoader.loadGraphFromJson("src/test/resources/k5_r2_s3_False.json");
        assertThat(ramseyGraph.run(graph2)).isFalse();
    }
    @Test
    public void GraphTest3() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(2,2);
        var graph3 = FileLoader.loadGraphFromJson("src/test/resources/Empty_graph_r2_s2_False.json");
        assertThat(ramseyGraph.run(graph3)).isFalse();
    }
    @Test
    public void GraphTest4() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(2, 4);
        var graph4 = FileLoader.loadGraphFromJson("src/test/resources/k5_r2_s4_False.json");
        assertThat(ramseyGraph.run(graph4)).isFalse();
    }
    @Test
    public void GraphTest5() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(2, 3);
        var graph5 = FileLoader.loadGraphFromJson("src/test/resources/C6_r2_s3_False.json");
        assertThat(ramseyGraph.run(graph5)).isFalse();
    }
    @Test
    public void GraphTest6() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(3, 2);
        var graph6 = FileLoader.loadGraphFromJson("src/test/resources/k3_r3_s2_True.json");
        assertThat(ramseyGraph.run(graph6)).isFalse();
    }
    @Test
    public void GraphTest7() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(3, 2);
        var graph7 = FileLoader.loadGraphFromJson("src/test/resources/k4_r3_s2_True.json");
        assertThat(ramseyGraph.run(graph7)).isFalse();
    }
    @Test
    public void GraphTest8() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(2, 2);
        var graph1 = FileLoader.loadGraphFromJson("src/test/resources/graph-data-k22.json");
        assertThat(ramseyGraph.run(graph1)).isFalse();
    }

}

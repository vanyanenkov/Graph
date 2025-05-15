import ru.leti.RamseyGraph;
import ru.leti.wise.task.graph.util.FileLoader;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RamseyGraphTest {

    @Test
    public void GraphTest0() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(3, 3);
        var graph1 = FileLoader.loadGraphFromJson("src/test/resources/new.json");
        assertThat(ramseyGraph.run(graph1)).isFalse();
    }


    @Test
    public void GraphTest1() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(2, 3);
        var graph1 = FileLoader.loadGraphFromJson("src/test/resources/k6_r2_s3_True.json");
        assertThat(ramseyGraph.run(graph1)).isTrue();
    }


    @Test
    public void GraphTest2() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(3, 3); //и для бОльших
        var graph2 = FileLoader.loadGraphFromJson("src/test/resources/4vertex.json");
        assertThat(ramseyGraph.run(graph2)).isFalse();
    }

    @Test
    public void GraphTest3() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(2,2);
        var graph3 = FileLoader.loadGraphFromJson("src/test/resources/4vertex.json");
        assertThat(ramseyGraph.run(graph3)).isTrue();
    }


    @Test
    public void GraphTest4() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(2,3);
        var graph4 = FileLoader.loadGraphFromJson("src/test/resources/Empty_graph_r2_s2_False.json");
        assertThat(ramseyGraph.run(graph4)).isFalse();
    }

    @Test
    public void GraphTest5() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(3,3);
        var graph5 = FileLoader.loadGraphFromJson("src/test/resources/5vertex.json");
        assertThat(ramseyGraph.run(graph5)).isFalse();
    }

    @Test
    public void GraphTest6() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(3,3);
        var graph6 = FileLoader.loadGraphFromJson("src/test/resources/6vertex_with_hole.json");
        assertThat(ramseyGraph.run(graph6)).isFalse();
    }


    // 7/21
    @Test
    public void GraphTest7() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(3,3);
        var graph7 = FileLoader.loadGraphFromJson("src/test/resources/7.json");
        assertThat(ramseyGraph.run(graph7)).isTrue();
    }

    @Test
    public void GraphTest8() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(4,4);
        var graph8 = FileLoader.loadGraphFromJson("src/test/resources/7.json");
        assertThat(ramseyGraph.run(graph8)).isFalse();
    }

    @Test
    public void GraphTest9() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(3, 3);
        var graph9 = FileLoader.loadGraphFromJson("src/test/resources/k6.json");
        assertThat(ramseyGraph.run(graph9)).isTrue();
    }


    @Test
    public void GraphTest10() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(3, 3);
        var graph10 = FileLoader.loadGraphFromJson("src/test/resources/3vertex.json");
        assertThat(ramseyGraph.run(graph10)).isFalse();
    }


    @Test
    public void GraphTest11() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(2, 3);
        var graph11 = FileLoader.loadGraphFromJson("src/test/resources/z.json");
        assertThat(ramseyGraph.run(graph11)).isTrue();
    }

    @Test
    public void GraphTest12() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(3, 2);
        var graph12 = FileLoader.loadGraphFromJson("src/test/resources/z.json");
        assertThat(ramseyGraph.run(graph12)).isTrue();
    }

    @Test
    public void GraphTest13() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(3, 2);
        var graph13 = FileLoader.loadGraphFromJson("src/test/resources/a.json");
        assertThat(ramseyGraph.run(graph13)).isTrue();
    }

    @Test
    public void GraphTest14() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(2, 3);
        var graph14 = FileLoader.loadGraphFromJson("src/test/resources/a.json");
        assertThat(ramseyGraph.run(graph14)).isTrue();
    }

    /*

    @Test
    public void GraphTest2() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(2, 3);
        var graph2 = FileLoader.loadGraphFromJson("src/test/resources/k5_r2_s3_False.json");
        assertThat(ramseyGraph.run(graph2)).isTrue();
    }

    */

    /*
    @Test
    public void GraphTest4() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(2, 3);
        var graph4 = FileLoader.loadGraphFromJson("src/test/resources/k5_r2_s4_False.json");
        assertThat(ramseyGraph.run(graph4)).isFalse();
    }
    @Test
    public void GraphTest5() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(2, 3);
        var graph5 = FileLoader.loadGraphFromJson("src/test/resources/C6_r2_s3_False.json");
        assertThat(ramseyGraph.run(graph5)).isTrue();
    }
    @Test
    public void GraphTest6() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(3, 2);
        var graph6 = FileLoader.loadGraphFromJson("src/test/resources/k3_r3_s2_True.json");
        assertThat(ramseyGraph.run(graph6)).isTrue();
    }

    @Test
    public void GraphTest7() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(3, 2);
        var graph7 = FileLoader.loadGraphFromJson("src/test/resources/k4_r3_s2_True.json");
        assertThat(ramseyGraph.run(graph7)).isTrue();
    }


    @Test
    public void GraphTest8() throws FileNotFoundException {
        RamseyGraph ramseyGraph = new RamseyGraph(2, 2);
        var graph8 = FileLoader.loadGraphFromJson("src/test/resources/graph-data-k22.json");
        assertThat(ramseyGraph.run(graph8)).isFalse();
    }

     */



}

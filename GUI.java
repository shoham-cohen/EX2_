

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class GUI extends JPanel{
    private DirectedWeightedGraphAlgorithms GraphAlgo;
    private JButton shortestPathDistButton = new JButton();
    private JButton shortestPathButton = new JButton();
    private JButton centerButton = new JButton();
    private JButton tspButton = new JButton();
    private JButton isconnected = new JButton();
    private JButton loadButton = new JButton();
    private JButton addNodeButton = new JButton();
    private JButton removeNodeButton = new JButton();
    private JButton connectButton = new JButton();
    private JButton removeEdgeButton = new JButton();
    private final JCheckBox edgesCheckBox = new JCheckBox("Edges");
    private double minx = 100000;
    private double miny = 100000;
    private double maxx = 0;
    private double maxy = 0;
    private double scaleX = -1;
    private double scaleY = -1;
    private List<NodeData> list = new LinkedList<>();
    private NodeData TheCenter;

    public GUI(DirectedWeightedGraphAlgorithms GraphAlgo){
        this.GraphAlgo = GraphAlgo;
        scale();
    }


    public void scale(){
       Iterator <NodeData> it = this.GraphAlgo.graph.nodeIter();
       while (it.hasNext()){
           NodeData node = it.next();
           if (node.pos.x > maxx){
               maxx = node.pos.x;
           }
           if (node.pos.x < minx){
               minx = node.pos.x;
           }
           if (node.pos.y > maxy){
               maxy = node.pos.y;
           }
           if (node.pos.y < miny){
               miny = node.pos.y;
           }
       }
       double ABSX = Math.abs(maxx - minx);
       double ABSY = Math.abs(maxy - miny);
       scaleX = 1100/ABSX;
       scaleY = 500/ABSY;
    }

     private void ButtonsCreator(){

         edgesCheckBox.setLocation(0, 30);
         this.add(edgesCheckBox);
        shortestPathDistButton.setLocation(0, 0);
        shortestPathDistButton.setSize(120, 15);
        shortestPathDistButton.setText("shortest path dist");
        this.add(shortestPathDistButton);

         shortestPathButton.setLocation(120, 0);
         shortestPathButton.setSize(120, 15);
         shortestPathButton.setText("shortest path");
         this.add(shortestPathButton);

         centerButton.setLocation(240, 0);
         centerButton.setSize(120, 15);
         centerButton.setText("center");
         this.add(centerButton);

         tspButton.setLocation(360, 0);
         tspButton.setSize(120, 15);
         tspButton.setText("tsp");
         this.add(tspButton);

         isconnected.setLocation(480, 0);
         isconnected.setSize(120, 15);
         isconnected.setText("is connected");
         this.add(isconnected);

         loadButton.setLocation(0, 15);
         loadButton.setSize(120, 15);
         loadButton.setText("load");
         this.add(loadButton);

         addNodeButton.setLocation(120, 15);
         addNodeButton.setSize(120, 15);
         addNodeButton.setText("add Node");
         this.add(addNodeButton);

         removeNodeButton.setLocation(240, 15);
         removeNodeButton.setSize(120, 15);
         removeNodeButton.setText("remove Node");
         this.add(removeNodeButton);

         connectButton.setLocation(360, 15);
         connectButton.setSize(120, 15);
         connectButton.setText("connect");
         this.add(connectButton);

         removeEdgeButton.setLocation(480, 15);
         removeEdgeButton.setSize(120, 15);
         removeEdgeButton.setText("remove Edge");
         this.add(removeEdgeButton);

         if (shortestPathDistButton.getActionListeners().length == 0){
             shortestPathDistButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     int src = -1;
                     int des = -1;
                     String Ssrc = JOptionPane.showInputDialog("enter src Node ID");
                     if (Ssrc != null){
                          src = Integer.parseInt(Ssrc);
                     }
                     String Sdes = JOptionPane.showInputDialog("enter dest Node ID");
                     if(Sdes != null){
                          des = Integer.parseInt(Sdes);
                     }
                     if(src != -1 && des != -1){
                         double dist = GraphAlgo.shortestPathDist(src, des);
                         JOptionPane.showMessageDialog(null ,"the smallest distance is: "+dist);
                     }
                 }
             });
         }
         if (shortestPathButton.getActionListeners().length == 0){
             shortestPathButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     int src = -1;
                     int des = -1;
                     String Ssrc = JOptionPane.showInputDialog("enter src Node ID");
                     if(Ssrc != null){
                         src = Integer.parseInt(Ssrc);
                     }
                     String Sdes = JOptionPane.showInputDialog("enter des Node ID");
                     if(Sdes != null){
                         des = Integer.parseInt(Sdes);
                     }
                     if(src != -1 && des != -1){
                         list.clear();
                         list = GraphAlgo.shortestPath(src , des);
                         JOptionPane.showMessageDialog(null, "the nodes are now colored with black");
                     }
                 }
             });
         }
         if (centerButton.getActionListeners().length == 0){
             centerButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     TheCenter = GraphAlgo.center();
                     if (TheCenter != null){
                         JOptionPane.showMessageDialog(null, "The center node id is: "+TheCenter.id+" and his color is now green");
                     }
                 }
             });
         }
         if (tspButton.getActionListeners().length == 0){
             tspButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     String Slength = JOptionPane.showInputDialog("enter the number of cities you would like to visit");
                     int length = Integer.parseInt(Slength);
                     List <NodeData> cities = new LinkedList<>();
                     for (int i = 0; i < length; i++){
                         String Sid = JOptionPane.showInputDialog("enter node id");
                         int id = Integer.parseInt(Sid);
                         cities.add(GraphAlgo.graph.nodes.get(id));
                     }
                     list.clear();
                     list = GraphAlgo.tsp(cities);
                 }
             });
         }
         if (isconnected.getActionListeners().length == 0){
             isconnected.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     if (GraphAlgo.isConnected()){
                         JOptionPane.showMessageDialog(null, "The graph is connected");
                     }
                     else{
                         JOptionPane.showMessageDialog(null,"The graph is not connected");
                     }
                 }
             });
         }
         if (loadButton.getActionListeners().length == 0){
             loadButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     String Name = JOptionPane.showInputDialog("enter the file name");
                     if(Name != null){
                         if (GraphAlgo.load(Name)){
                             JOptionPane.showMessageDialog(null, "the graph has been loaded successfully");
                             minx = 100000;
                             miny = 100000;
                             maxx = 0;
                             maxy = 0;
                             scaleX = -1;
                             scaleY = -1;
                             list = new LinkedList<>();
                             TheCenter = null;
                             scale();
                         }
                         else{
                             JOptionPane.showMessageDialog(null, "something went wrong");
                         }
                     }
                 }
             });
         }
         if (addNodeButton.getActionListeners().length == 0){
             addNodeButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     String Sid = JOptionPane.showInputDialog("enter id, you can enter exist id: the node will be override");
                     int id = Integer.parseInt(Sid);
                     String Sx = JOptionPane.showInputDialog("enter double for x");
                     double x = Double.parseDouble(Sx);
                     String Sy = JOptionPane.showInputDialog("enter double for y");
                     double y = Double.parseDouble(Sy);
                     String Sz = JOptionPane.showInputDialog("enter double for z");
                     double z = Double.parseDouble(Sz);
                     GeoLocation pos = new GeoLocation(x, y, z);
                     NodeData nodeData = new NodeData(pos, id, null);
                     GraphAlgo.graph.addNode(nodeData);
                     minx = 100000;
                     miny = 100000;
                     maxx = 0;
                     maxy = 0;
                     scaleX = -1;
                     scaleY = -1;
                     list = new LinkedList<>();
                     scale();
                     JOptionPane.showMessageDialog(null, "the node has been added successfully");



                 }
             });
         }
         if (removeNodeButton.getActionListeners().length == 0){
             removeNodeButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     String Sid = JOptionPane.showInputDialog("enter the id of the node you would like to remove");
                     if (Sid != null) {
                         int id = Integer.parseInt(Sid);
                         try {
                             GraphAlgo.graph.removeNode(id);
                             if (TheCenter.id == id){
                                 TheCenter = null;
                             }
                             JOptionPane.showMessageDialog(null, "The node has been removed successfully");

                         } catch (Exception ex) {
                             ex.printStackTrace();
                         }
                     }
                 }
             });
         }
         if (connectButton.getActionListeners().length == 0){
             connectButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     int src, des;
                     double w;
                     String Ssrc = JOptionPane.showInputDialog("enter src id");
                     if (Ssrc != null){
                         src = Integer.parseInt(Ssrc);
                         String Sdes = JOptionPane.showInputDialog("enter des id");
                         if (Sdes != null){
                             des = Integer.parseInt(Sdes);
                             String Sw = JOptionPane.showInputDialog("enter edge weight");
                             if (Sw != null){
                                 w = Double.parseDouble(Sw);
                                 try {
                                     GraphAlgo.graph.connect(src, des, w);
                                     JOptionPane.showMessageDialog(null, "edge has been added successfully");
                                 } catch (Exception ex) {
                                     ex.printStackTrace();
                                 }
                             }
                         }
                     }
                 }
             });
         }
         if (removeEdgeButton.getActionListeners().length == 0){
             removeEdgeButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     int src, des;
                    String Ssrc = JOptionPane.showInputDialog("enter src id");
                    if(Ssrc != null){
                        src = Integer.parseInt(Ssrc);
                        String Sdes = JOptionPane.showInputDialog("enter des id");
                        if(Sdes != null){
                            des = Integer.parseInt(Sdes);
                            try {
                                GraphAlgo.graph.removeEdge(src, des);
                                JOptionPane.showMessageDialog(null, "edge has been removed successfully");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                 }
             });
         }
    }

    public void ScaleNodes(Graphics graphics){
        if(scaleY == -1 || scaleX == -1){
            scale();
        }
        Iterator<NodeData> nodesIterator = GraphAlgo.graph.nodeIter();
        graphics.setColor(Color.ORANGE);
        while (nodesIterator.hasNext()){
            NodeData curr = nodesIterator.next();
            double currX = (curr.pos.x)*scaleX;
            double currY = (curr.pos.y)*scaleY;
            if(list.contains(curr)){
                graphics.setColor(Color.BLACK);
            }
            if(TheCenter == curr){
                graphics.setColor(Color.GREEN);
            }
            graphics.fillOval((int) currX + 30, (int) currY + 50, 8, 8);
            graphics.setColor(Color.red);
            graphics.drawString(curr.getKey()+"", (int)currX + 30, (int)currY + 50);
            graphics.setColor(Color.ORANGE);
        }
    }

    public void ScaleEdges(Graphics graphics){
        if(scaleY == -1 || scaleX == -1){
            scale();
        }
        Iterator<EdgeData> edgesIterator = GraphAlgo.graph.edgeIter();
        graphics.setColor(Color.black);
        while(edgesIterator.hasNext()){
            EdgeData curr = edgesIterator.next();
            int currsrcX = (int) ((GraphAlgo.graph.nodes.get(curr.src).pos.x)*scaleX) + 30;
            int currdesX = (int) ((GraphAlgo.graph.nodes.get(curr.des).pos.x)*scaleX) + 30;
            int currsrcY = (int) ((GraphAlgo.graph.nodes.get(curr.src).pos.y)*scaleY) + 50;
            int currdesY = (int) ((GraphAlgo.graph.nodes.get(curr.des).pos.y)*scaleY) + 50;
            graphics.drawLine(currsrcX, currsrcY, currdesX, currdesY);
        }
    }

    @Override
    public void paintComponent (Graphics graphics) {
        super.paintComponent(graphics);

        this.setVisible(false);
        ButtonsCreator();
        ScaleNodes(graphics);
        if((edgesCheckBox.isSelected())){
            ScaleEdges(graphics);
        }
        this.setVisible(true);

    }
}

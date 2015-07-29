package ua.woodyutilities.util;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ua.woodyutilities.entity.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor Alex Iakovenko
 * Date: 7/28/15
 * Time: 1:57 PM
 */
public class PartBuilder {
    public static final Logger LOGGER = Logger.getLogger(PartBuilder.class);

   private Part part;
   private Element element;

   public Part buildRectangle(Element element){
       this.element = element;
       part = new Part();
       fillAttributes();

       Element nodeLeftEdge = (Element)element.getElementsByTagName(Part.ELEM_LEFT_EDGE).item(0);
       part.setLeftEdge(fillEdge(nodeLeftEdge));

       Element nodeRightEdge = (Element)element.getElementsByTagName(Part.ELEM_RIGHT_EDGE).item(0);
       part.setRightEdge(fillEdge(nodeRightEdge));

       Element nodeTopEdge = (Element)element.getElementsByTagName(Part.ELEM_TOP_EDGE).item(0);
       part.setTopEdge(fillEdge(nodeTopEdge));

       Element nodeBottomEdge = (Element)element.getElementsByTagName(Part.ELEM_BOTTOM_EDGE).item(0);
       part.setBottomEdge(fillEdge(nodeBottomEdge));

       fillRectangleElements();

       return part;
   }

   public Part buildCurviLinear(Element element){
       this.element = element;
       part = new Part();
       fillAttributes();
       fillCurviLinearElements();

       return part;
   }

   private void fillAttributes(){
       part.setName(element.getAttribute(Part.ATTR_NAME));
       part.setType(element.getAttribute(Part.ATTR_TYPE));
       part.setCode(element.getAttribute(Part.ATTR_CODE));
       part.setMatClass(element.getAttribute(Part.ATTR_MATCLASS));
       part.setMatName(element.getAttribute(Part.ATTR_MATNAME));
       part.setMatId(element.getAttribute(Part.ATTR_MAT_ID));
       part.setSizeH(element.getAttribute(Part.ATTR_SIZE_H));
       part.setSizeX(element.getAttribute(Part.ATTR_SIZE_X));
       part.setSizeY(element.getAttribute(Part.ATTR_SIZE_Y));
       part.setSizeXg(element.getAttribute(Part.ATTR_SIZE_XG));
       part.setSizeYg(element.getAttribute(Part.ATTR_SIZE_YG));
   }

    private void fillRectangleElements(){
        Element holes = (Element)element.getElementsByTagName("Holes").item(0);
        NodeList drills = holes.getElementsByTagName("Drill");
        List<Drill> drillList = new ArrayList<>();
        for (int i = 0; i < drills.getLength(); i++){
            Drill drill = new Drill();
            Element drillNode = (Element) drills.item(i);
            drill.setDiameter(drillNode.getAttribute(Drill.ATTR_DIAMETER));
            drill.setCountersinkIn(drillNode.getAttribute(Drill.ATTR_COUNTERSINK_IN));
            drill.setDepth(drillNode.getAttribute(Drill.ATTR_DEPTH));
            Element vector = (Element)drillNode.getElementsByTagName(Drill.ELEM_VECTOR).item(0);
            Element point = (Element)drillNode.getElementsByTagName(Drill.ELEM_POINT).item(0);

            drill.setVector(fillCoordinates(vector));
            drill.setPoint(fillCoordinates(point));

            drillList.add(drill);
        }

        part.setHoles(new Holes(drillList));
    }

    private void fillCurviLinearElements(){
        Node cPolyLineNode = element.getFirstChild();
        NodeList lineSegments = cPolyLineNode.getChildNodes();
        CPolyLine cPolyLine = new CPolyLine();

        String type = ((Element)cPolyLineNode).getAttribute(CPolyLine.ATTR_TYPE);
        cPolyLine.setType(type);
        List<LineSegment> lineSegmentsList = new ArrayList<>();
        for (int i = 0; i < lineSegments.getLength(); i++){
            Element elementLineSegment = (Element)lineSegments.item(i);
            Element elementEdge = (Element)elementLineSegment.getElementsByTagName(LineSegment.ELEM_EDGE).item(0);
            Element elementStartPoint = (Element)elementLineSegment.getElementsByTagName(LineSegment.ELEM_START_POINT).item(0);
            Element elementEndPoint = (Element)elementLineSegment.getElementsByTagName(LineSegment.ELEM_END_POINT).item(0);
            LineSegment segment = new LineSegment();

            segment.setProperty(elementLineSegment.getAttribute(LineSegment.ATTR_PROPERTY));
            segment.setEdge(fillEdge(elementEdge));

            Element elemPoint = (Element)elementStartPoint.getElementsByTagName("Point").item(0);
            Point point = new Point(fillCoordinates(elemPoint));
            segment.setStartPoint(point);

            elemPoint = (Element)elementEndPoint.getElementsByTagName("Point").item(0);
            point = new Point(fillCoordinates(elemPoint));
            segment.setEndPoint(point);

            lineSegmentsList.add(segment);

        }
        cPolyLine.setLineSegments(lineSegmentsList);
        part.setcPolyLine(cPolyLine);


    }

    private Coordinates fillCoordinates(Element e){

        String x = e.getAttribute("x");
        String y = e.getAttribute("y");
        String z = e.getAttribute("z");

        return new Coordinates(x, y, z);

    }

    private Edge fillEdge(Element e){
        String matclass = e.getAttribute(Edge.ATTR_MATCLASS);
        String matName = e.getAttribute(Edge.ATTR_MATNAME);
        String matId = e.getAttribute(Edge.ATTR_MAT_ID);


        return new Edge(matclass, matName, matId);
    }





}

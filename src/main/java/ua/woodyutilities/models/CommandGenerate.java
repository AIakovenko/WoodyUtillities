package ua.woodyutilities.models;

import org.apache.log4j.Logger;
import org.w3c.dom.*;
import ua.woodyutilities.dto.Edgeband;
import ua.woodyutilities.dto.PartDTO;
import ua.woodyutilities.entity.Edge;
import ua.woodyutilities.entity.Part;
import ua.woodyutilities.entity.PartType;
import ua.woodyutilities.util.LocalizationManager;
import ua.woodyutilities.util.PartBuilder;
import ua.woodyutilities.util.PropertyManager;
import ua.woodyutilities.views.Material;
import ua.woodyutilities.views.StatusBar;
import ua.woodyutilities.xml.XMLDocument;

import javax.swing.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.*;


/**
 * @autor Alex Iakovenko
 * Date: 6/21/15
 * Time: 4:19 PM
 */
public class CommandGenerate implements Command {
    private static final Logger logger = Logger.getLogger(CommandGenerate.class);
    private static final int TO_MILLIMETERS = 1000;
    private final LocalizationManager LM = LocalizationManager.getInstance();
    private StatusBar statusBar = StatusBar.getInstance();
    private final PropertyManager PM = PropertyManager.getInstance();
    private File destinationFolder;
    private List<PartDTO> parts;
    private Set<Edgeband> usedEdgebands;

    public void execute() {
        Material materials = Material.getInstance();
        List<String> table = materials.getSelectedMaterials();

        destinationFolder = showFileOpenDialog();
        if (destinationFolder != null) {
            table.forEach(s -> generateFile(s));
        }


    }

    private void generateFile(String material) {
        String errorMessage;
        String destination = destinationFolder.getAbsolutePath() + "/";
        if (destination == null){
            destination = PM.getValue(PropertyManager.PATH_EXPORTED_FILES) + "/";
        }
        File outFile = new File(destination + material + ".xml");

        parts = new LinkedList<>();
        usedEdgebands = new TreeSet<>();

        try (
                PrintWriter out = new PrintWriter(outFile, "UTF-8");
                BufferedWriter bw = new BufferedWriter(out)
        ) {
            String head = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<Object>\n" +
                    "\t<Administrative>\n" +
                    "\t\t<Title></Title>\n" +
                    "\t\t<Author></Author>\n" +
                    "\t\t<Manager></Manager>\n" +
                    "\t\t<Company></Company>\n" +
                    "\t\t<Category></Category>\n" +
                    "\t\t<Keywords/>\n" +
                    "\t\t<Comments></Comments>\n" +
                    "\t\t<HyperlinkBase/>\n" +
                    "\t</Administrative>\n" +
                    "\t<Model>\n" +
                    "\t\t<Product \n" +
                    "\t\t\tname=\"\" \n" +
                    "\t\t\tcode=\"\" \n" +
                    "\t\t\tGUID=\"\" \n" +
                    "\t\t\tORDER_NUMBER=\"\" \n" +
                    "\t\t\tORDER_CODE=\"\" \n" +
                    "\t\t\tORDER_COST=\"\" \n" +
                    "\t\t\tORDER_DATE1=\"\" \n" +
                    "\t\t\tORDER_DATE2=\"\" \n" +
                    "\t\t\tORDER_NOTE=\"\" \n" +
                    "\t\t\tORDER_DISCOUNT=\"\" \n" +
                    "\t\t\tORDER_METERING=\"\" \n" +
                    "\t\t\tORDER_TWIST=\"\" \n" +
                    "\t\t\tACCOUNT_NUMBER=\"\" \n" +
                    "\t\t\tCONTRACT_NUMBER=\"\" \n" +
                    "\t\t\tCONTRACT_DATE=\"\">\n";
            bw.write(head);
            String body = createBody(material);
            bw.write(body);
            String tail = "\t\t</Product>\n" +
                    "\t</Model>\n" +
                    "</Object>";
            bw.write(tail);

            writeHTMFile(material);

            statusBar.addStatus(material + LM.getProperty("MESSAGE_GENERATE_SUCCESS"), false);
            statusBar.addStatus(parts.size() + " parts has been operated", false);
            parts.forEach(part -> System.out.println(part.getName()));


        } catch (FileNotFoundException e) {
            errorMessage = material + LM.getProperty("MESSAGE_IO_ERROR");
            statusBar.addStatus(errorMessage, false);
            logger.error(errorMessage, e);

        } catch (IOException e) {
            errorMessage = material + LM.getProperty("MESSAGE_IO_ERROR");
            statusBar.addStatus(errorMessage, false);
            logger.error(errorMessage, e);
        }

    }

    private String createBody(String material) {
        XMLDocument document = XMLDocument.getInstance();

        Document doc = document.getDocument();
        doc.getDocumentElement().normalize();
        StringBuilder body = new StringBuilder();

            NodeList nList = doc.getElementsByTagName("Part");
            PartBuilder partBuilder = new PartBuilder();

            List<Part> parts = new ArrayList<>();
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    String currentMaterial = eElement.getAttribute(Part.ATTR_MATNAME);
                    if (currentMaterial.equals(material)) {
                        if (eElement.getAttribute(Part.ATTR_TYPE).equals(PartType.RECTANGLE)) {
                            Part part = partBuilder.buildRectangle(eElement);
                            parts.add(part);

                        } else {
                            Part part = partBuilder.buildCurviLinear(eElement);
                            parts.add(part);
                        }
                        writePartInfo(eElement);
                    }
                }
            }
            parts.forEach(part -> {
                body.append(part.toString());
            });

        return body.toString();
    }

    private File showFileOpenDialog() {
        File destination = null;
        JFileChooser fileOpen = new JFileChooser("." + File.separator);
        fileOpen.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int ret = fileOpen.showDialog(null, LM.getProperty("TITLE_CHOOSE_DESTINATION"));
        if (ret == JFileChooser.APPROVE_OPTION) {
            destination = fileOpen.getSelectedFile();
        }
        return destination;
    }

    private void writePartInfo(Element element){
        PartDTO partDTO = new PartDTO();
        partDTO.setName(element.getAttribute("name"));
        partDTO.setMatName(element.getAttribute("matname"));
        partDTO.setThickness(Double.parseDouble(element.getAttribute("SizeH")) * TO_MILLIMETERS);
        partDTO.setWidth(Double.parseDouble(element.getAttribute("SizeX")) * TO_MILLIMETERS);
        partDTO.setHeight(Double.parseDouble(element.getAttribute("SizeY")) * TO_MILLIMETERS);
        partDTO.setTotalWidth(Double.parseDouble(element.getAttribute("SizeXg")) * TO_MILLIMETERS);
        partDTO.setTotalHeight(Double.parseDouble(element.getAttribute("SizeYg")) * TO_MILLIMETERS);



        Element leftEdge;
        Element rightEdge;
        Element topEdge;
        Element bottomEdge;

        if (element.getAttribute("type").equals("rectangle")){
            NodeList nodes = element.getChildNodes();
            leftEdge = (Element)nodes.item(0);
            rightEdge = (Element)nodes.item(1);
            topEdge = (Element)nodes.item(2);
            bottomEdge = (Element)nodes.item(3);

            partDTO.putEdge(PartDTO.LEFT_EDGE, createEdgeband(leftEdge));
            partDTO.putEdge(PartDTO.BOTTOM_EDGE, createEdgeband(bottomEdge));
            partDTO.putEdge(PartDTO.RIGHT_EDGE, createEdgeband(rightEdge));
            partDTO.putEdge(PartDTO.TOP_EDGE, createEdgeband(topEdge));
        }

        parts.add(partDTO);

    }

    private Edgeband createEdgeband(Element element){
        final double EDGEBAND_THICKNESS = 0.35;
        final int EDGEBAND_WIDTH = 22;
        String id = element.getAttribute("mat_id");
        String matname = element.getAttribute("matname");
        Long materialId;
        if (!id.equals("")){
            materialId = Long.parseLong(id);
        } else {
            materialId = 0l;
        }

        Edgeband edgeband = new Edgeband(materialId, matname, EDGEBAND_THICKNESS, EDGEBAND_WIDTH);
        return edgeband;
    }

    private void writeHTMFile(String material){


        String errorMessage;
        String destination = destinationFolder.getAbsolutePath() + "/";
        if (destination == null){
            destination = PM.getValue(PropertyManager.PATH_EXPORTED_FILES) + "/";
        }
        File outFile = new File(destination + material + ".htm");
        try (
                PrintWriter out = new PrintWriter(outFile, "cp1251");
                BufferedWriter bw = new BufferedWriter(out)
        ) {
            String head = "<html>\n" +
                    "  <head>\n" +
                    "    <title>По деталям</title>\n" +
                    "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1251\">\n" +
                    "  </head>\n" +
                    "<body>\n" +
                    "  <TABLE>\n" +
                    "    <THEAD> \n" +
                    "    </THEAD>\n";

            bw.write(head);
            String body = createHTMBody();
            bw.write(body);
            String tail = "</body>\n" +
                    "</html>";
            bw.write(tail);

            statusBar.addStatus(material + LM.getProperty("MESSAGE_GENERATE_HTM_SUCCESS"), false);


        } catch (FileNotFoundException e) {
            errorMessage = material + LM.getProperty("MESSAGE_IO_ERROR");
            statusBar.addStatus(errorMessage, false);
            logger.error(errorMessage, e);

        } catch (IOException e) {
            errorMessage = material + LM.getProperty("MESSAGE_IO_ERROR");
            statusBar.addStatus(errorMessage, false);
            logger.error(errorMessage, e);
        }
    }

    private String createHTMBody(){
        StringBuilder sb = new StringBuilder();

        parts.forEach(part -> {
            Collection<Edgeband> edgebands = part.getEdge();
            edgebands.forEach(edgeband-> {
                usedEdgebands.add(edgeband);
            });
            Map<Edgeband, Integer> edgebandMap = new HashMap<Edgeband, Integer>();
            int index = 1;
            for (Edgeband edgeband : usedEdgebands){
                if (edgebands.contains(edgeband) && edgeband.getMaterialId() != 0){
                    edgebandMap.put(edgeband, index++);
                }


            }
            Integer topEdge = edgebandMap.get(part.getEdge(PartDTO.TOP_EDGE));
            Integer leftEdge = edgebandMap.get(part.getEdge(PartDTO.LEFT_EDGE));
            Integer rightEdge = edgebandMap.get(part.getEdge(PartDTO.RIGHT_EDGE));
            Integer bottomEdge = edgebandMap.get(part.getEdge(PartDTO.BOTTOM_EDGE));

            String item =
                    "    <TR>\n" +
                    "      <TD>" + part.getName()+ "</TD>\n" +
                    "      <TD>" + part.getMatName() + "</TD>\n" +
                    "      <TD>" + part.getThickness() + "</TD>\n" +
                    "      <TD>" + String.format("%.1f", part.getTotalWidth())+ " x " + String.format("%.1f", part.getTotalHeight())+ "</TD>\n" +
                    "      <TD>" + String.format("%.1f", part.getWidth()) + " x " + String.format("%.1f", part.getHeight())+ "</TD>\n" +
                    "      <TD>\n" +
                    "\t<DIV>\n" +
                    "\t  <TABLE>\n" +
                    "\t    <TR>\n" +
                    "\t      <TD><NOBR>&nbsp;" + (leftEdge != null ? String.valueOf(leftEdge) + " (1)" : "&nbsp;") + "</NOBR></TD>\n" +
                    "\t      <TD>\n" +
                    "\t\t <P><NOBR>&nbsp;" + (topEdge != null ? String.valueOf(topEdge) + " (4)" : "&nbsp;") + "</NOBR><BR>"+
                    "<NOBR>&nbsp;" + (bottomEdge != null ? String.valueOf(bottomEdge) + " (2)" : "&nbsp;") +"</NOBR></P>\n" +
                    "\t      </TD>\n" +
                    "\t      <TD><P><NOBR>&nbsp;"+ (rightEdge != null ? String.valueOf(rightEdge) + " (3)" : "&nbsp;") +"</NOBR></P></TD>\n" +
                    "\t    </TR>\n" +
                    "\t  </TABLE>\n" +
                    "\t</DIV>\n" +
                    "      </TD>\n";
            sb.append(item);
        });
        String tableHead = "</TABLE>\n" +
                "<H3></H3>\n" +
                "<TABLE>\n" +
                " <THEAD>\n" +
                "  <TH>№ п/п</TH>\n" +
                "  <TH>Название</TH>\n" +
                "  <TH>Ширина</TH>\n" +
                "  <TH>Толщина</TH>\n" +
                " </THEAD>\n" ;

        sb.append(tableHead);
        int tableIndex = 1;
        for (Edgeband edgeband : usedEdgebands){
            if (!edgeband.getName().equals("")) {
                String item = "<TR>\n" +
                        "  <TD>" + tableIndex++ + "</TD>\n" +
                        "  <TD>" + edgeband.getName() + "</TD>\n" +
                        "  <TD>" + edgeband.getWidth() + "</TD>\n" +
                        "  <TD>" + String.format("%.1f", edgeband.getThickness()) + "</TD>\n" +
                        " </TR>\n";

                sb.append(item);
            }

        }
        String tableTail = "</TABLE>\n";
        sb.append(tableTail);
        return sb.toString();
    }



}

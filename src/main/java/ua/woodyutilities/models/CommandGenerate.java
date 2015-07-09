package ua.woodyutilities.models;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ua.woodyutilities.frames.MainFrame;
import ua.woodyutilities.util.LocalizationManager;
import ua.woodyutilities.util.PropertyManager;
import ua.woodyutilities.views.Material;
import ua.woodyutilities.views.StatusBar;
import ua.woodyutilities.xml.XMLDocument;

import javax.swing.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;

/**
 * @autor Alex Iakovenko
 * Date: 6/21/15
 * Time: 4:19 PM
 */
public class CommandGenerate implements Command {
    private final LocalizationManager LM = LocalizationManager.getInstance();
    private StatusBar statusBar = StatusBar.getInstance();
    private final PropertyManager PM = PropertyManager.getInstance();

    public void execute() {
        Material materials = Material.getInstance();
        List<String> table = materials.getSelectedMaterials();
        for (String s : table) {
            generateFile(s);
        }

    }

    private void generateFile(String material) {
        File outFile = new File(PM.getValue(PropertyManager.PATH_EXPORTED_FILES) + material + ".xml");
        try (
                FileWriter out = new FileWriter(outFile);
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

//            JOptionPane.showMessageDialog(
//                    MainFrame.getInstance(),
//                    LM.getProperty("MESSAGE_GENERATE_SUCCESS"),
//                    LM.getProperty("TITLE_GENERATE_FILE"),
//                    JOptionPane.INFORMATION_MESSAGE);
            statusBar.addStatus(material + LM.getProperty("MESSAGE_GENERATE_SUCCESS"), false);


        } catch (FileNotFoundException e) {
            statusBar.addStatus(material + LM.getProperty("MESSAGE_IO_ERROR"), false);
            e.printStackTrace();
        } catch (IOException e) {
            statusBar.addStatus(material + LM.getProperty("MESSAGE_IO_ERROR"), false);
            e.printStackTrace();
        }

    }

    private String createBody(String material) {
        XMLDocument document = XMLDocument.getInstance();

        Document doc = document.getDocument();
        doc.getDocumentElement().normalize();
        StringBuilder body = new StringBuilder();
        try {


            StreamResult result = new StreamResult(new StringWriter());
            NodeList nList = doc.getElementsByTagName("Part");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
//                Node nNode = nList.item(0);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    String currentMaterial = eElement.getAttribute("matname");
                    if (currentMaterial.equals(material)) {
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                        try {
                            DOMSource source = new DOMSource(nNode);
                            transformer.transform(source, result);

                        } catch (TransformerException e) {
                        }
                    }
                }
            }

            String xmlString = result.getWriter().toString();
            body.append(xmlString);
//            System.out.println(xmlString);

        } catch (TransformerConfigurationException e) {

        }
        return body.toString();
    }


}

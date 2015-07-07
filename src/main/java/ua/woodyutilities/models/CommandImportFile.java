package ua.woodyutilities.models;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ua.woodyutilities.data.DataFile;
import ua.woodyutilities.frames.MainFrame;
import ua.woodyutilities.util.LocalizationManager;
import ua.woodyutilities.views.Material;
import ua.woodyutilities.xml.XMLDocument;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/**
 * @autor Alex Iakovenko
 * Date: 6/21/15
 * Time: 10:04 AM
 */
public class CommandImportFile implements Command {
    private static final LocalizationManager LM = LocalizationManager.getInstance();
    private DataFile dataFile = DataFile.getInstance();


    @Override
    public void execute(){
        showFileOpenDialog();
        File importedFile = dataFile.getImportedFile();
        try {
            if (importedFile != null) {
                if (parse()) {
                    JOptionPane.showMessageDialog(
                            MainFrame.getInstance(),
                            LM.getProperty("MESSAGE_IMPORT_SUCCESS"),
                            LM.getProperty("TITLE_IMPORT_FILE"),
                            JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(
                            MainFrame.getInstance(),
                            LM.getProperty("MESSAGE_IMPORT_FAIL"),
                            LM.getProperty("TITLE_IMPORT_FILE"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    MainFrame.getInstance(),
                    LM.getProperty("MESSAGE_FILE_NOT_FOUND"),
                    LM.getProperty("TITLE_IMPORT_FILE"),
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void showFileOpenDialog() {
        JFileChooser fileOpen = new JFileChooser("./");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "XML files .xml", "xml");
        fileOpen.setFileFilter(filter);
        int ret = fileOpen.showDialog(null, "Open file");
        if (ret == JFileChooser.APPROVE_OPTION) {
            dataFile.setImportedFile(fileOpen.getSelectedFile());
        }
    }

    private boolean parse() throws IOException {
        XMLDocument document = XMLDocument.getInstance();
        Material materialTable = Material.getInstance();

        Set<String> materials = new TreeSet<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document.setDocument(dBuilder.parse(dataFile.getImportedFile()));
            Document doc = document.getDocument();
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Part");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    materials.add(eElement.getAttribute("matname"));
                }
            }
            for(String s : materials){
                materialTable.addRow(s);
            }
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }

}

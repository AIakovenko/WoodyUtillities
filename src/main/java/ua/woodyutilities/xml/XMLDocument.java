package ua.woodyutilities.xml;

import org.w3c.dom.Document;

/**
 * @autor Alex Iakovenko
 * Date: 6/21/15
 * Time: 12:30 PM
 */
public class XMLDocument {
    private static XMLDocument instance;
    private Document document;
    private XMLDocument(){}

    public static synchronized XMLDocument getInstance(){
        if (instance == null){
            instance = new XMLDocument();
        }
        return instance;
    }

    public void setDocument(Document document){
        this.document = document;
    }

    public Document getDocument(){
        return document;
    }
    public Object clone(){
        throw new UnsupportedOperationException();
    }
}

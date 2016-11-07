package com.yingjun.ssm.util;

import java.io.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

public class XMLConverter {

	private Marshaller marshaller;
	private Unmarshaller unmarshaller;

	public Marshaller getMarshaller() {
		return marshaller;
	}

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	//java object --> xml
    public String convertFromObjectToXML(Object obj) {
        String xml;
        StringWriter writer = null;
        try {
            writer = new StringWriter();
            getMarshaller().marshal(obj, new StreamResult(writer));
            xml = writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return xml;
    }
    //xml --> java object
    public Object convertFromXMLToObject(String xml) {
        Object obj = null;
        try {
            obj = getUnmarshaller().unmarshal(new StreamSource(new StringReader(xml)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    }


    // public void convertFromObjectToXML(Object object, String filepath)
	// 	throws IOException {
    //
	// 	FileOutputStream os = null;
	// 	try {
	// 		os = new FileOutputStream(filepath);
	// 		getMarshaller().marshal(object, new StreamResult(os));
	// 	} finally {
	// 		if (os != null) {
	// 			os.close();
	// 		}
	// 	}
	// }

	// public Object convertFromXMLToObject(String xmlfile) throws IOException {
    //
	// 	FileInputStream is = null;
	// 	try {
	// 		is = new FileInputStream(xmlfile);
	// 		return getUnmarshaller().unmarshal(new StreamSource(is));
	// 	} finally {
	// 		if (is != null) {
	// 			is.close();
	// 		}
	// 	}
	// }


}

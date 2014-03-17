package edu.uiowa.slis.graphtaglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EdgeSource extends TagSupport {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(EdgeSource.class);
	
	public int doStartTag() throws JspException {
		Edge theEdge = (Edge)findAncestorWithClass(this, Edge.class);
		log.trace("edge:" + theEdge.currentEdge);
		try {
			pageContext.getOut().print(theEdge.currentEdge.getSource().getID());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

    public int doEndTag() throws JspTagException, JspException {
        clearServiceState();
        return super.doEndTag();
    }

    private void clearServiceState() {
    }
}

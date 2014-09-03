package net.jueb.tools.log.uiLog.normalThread;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JTextAreaLogAppenderThread extends LogAppenderThread{

	private JTextArea textArea;
    private JScrollPane scroll;
    private boolean isScroll;
    
	public JTextAreaLogAppenderThread(String name, String pattern,JTextArea textArea, JScrollPane scroll) {
		super(name, pattern);
		this.textArea = textArea;
	    this.scroll = scroll;
	    if(this.scroll!=null)
	    {
	    	this.isScroll=true;
	    }
	}

	@Override
	protected void doOutLog(final String log) {
//		javax.swing.SwingUtilities.invokeLater(new Runnable() { //向UI线程发消息。
//		    public void run() {
//		    	textArea.append(log+"\n");
//		        if(isScroll)
//		        {//使垂直滚动条自动向下滚动
//		        	scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
//		        }
//		    }
//		});
		try {
			javax.swing.SwingUtilities.invokeAndWait(new Runnable() { //向UI线程发消息。
			    public void run() {
			    	textArea.append(log+"\n");
			        if(isScroll)
			        {//使垂直滚动条自动向下滚动
			        	scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
			        }
			    }
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}

}

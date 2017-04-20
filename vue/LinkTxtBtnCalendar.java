/**
 * 
 */
package vue;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

/**
 * @author test
 *
 */
public class LinkTxtBtnCalendar extends LinkTxtBtn {

	private Date theDate;





	/**
	 * @param txtDate
	 * @param btnCalDate
	 */
	public LinkTxtBtnCalendar(Point pointDialParent, JTextField txtDate, JButton btnCalDate) {
		super(pointDialParent, txtDate, btnCalDate);
		
		theDate = new Date();
		//DateFormat df =  DateFormat.getDateInstance(); 
		SimpleDateFormat sdf = new SimpleDateFormat("dd / MM / yyyy");
		((JTextField)txt).setText(sdf.format(theDate));
		
	}
	
	



	/* (non-Javadoc)
	 * @see vue.LinkTxtBtn#CallDial(int, int)
	 */
	@Override
	protected void CallDial(int x, int y) {

		CalendrierView dialog = new CalendrierView(null,x,y);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		theDate = dialog.showDialog();
		
		if (theDate !=null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd / MM / yyyy");
			((JTextField)txt).setText(sdf.format(theDate));
		}
		
		
	}





	/**
	 * @return the theDate
	 */
	public Date getDate() {
		return theDate;
	}





	/**
	 * @param theDate the theDate to set
	 */
	public void setDate(Date theDate) {
		this.theDate = theDate;
	}




}

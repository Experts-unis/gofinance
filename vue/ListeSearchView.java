package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.ControlManager;

import model.DataRefLibelle;
import model.ManagerRefLibelle;

import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListeSearchView extends DialogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtSearch;
	private JList<DataRefLibelle> liste;
	private DefaultListModel<DataRefLibelle> listeModel;
	Vector<DataRefLibelle> data;
	private JButton btnEditer;
	private JButton btnSupprimer;
	private JButton btnSelectionner;

	DataRefLibelle dataSelected;
	private ManagerRefLibelle manager;
	private JButton btnAjouter;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListeSearchView dialog = new ListeSearchView(null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListeSearchView(ManagerRefLibelle manager,JFrame owner) {
		super(null,owner, manager.getTitle(), true,350, 535);
		
		initComponents();
		
		this.manager = manager;
		dataSelected=null;
		
		initList();
		
		initTxtSearch();
		
		
		initButton();
		
	}

	/**
	 * 
	 */
	public void initButton() {
		btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionAjouter();
			}
		});
		btnAjouter.setBounds(10, 431, 89, 23);
		contentPanel.add(btnAjouter);
		
		btnEditer = new JButton("Editer");
		btnEditer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionEditer();
			}
		});
		btnEditer.setBounds(109, 431, 101, 23);
		contentPanel.add(btnEditer);
		
		

		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSupprimer();
			}
		});
		btnSupprimer.setBounds(220, 431, 104, 23);
		contentPanel.add(btnSupprimer);
		

		
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		btnSelectionner = new JButton("Selectionner");
		btnSelectionner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSelectionner();
			}
		});
		btnSelectionner.setActionCommand("OK");
		buttonPane.add(btnSelectionner);
		getRootPane().setDefaultButton(btnSelectionner);
					
		JButton btnQuitter = new JButton("Annuler");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionQuitter();
			}
		});
		btnQuitter.setActionCommand("Cancel");
		buttonPane.add(btnQuitter);
			
		setEnableButton(false);
	}

	/**
	 * 
	 */
	public void initTxtSearch() {
		
		txtSearch = new JTextField();
		txtSearch.setBounds(10, 400, 314, 20);
		contentPanel.add(txtSearch);
		txtSearch.setColumns(10);
		txtSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {							
				DefaultListModel model = new DefaultListModel();//creation dun nouveau model pour une JList
				String enteredText = txtSearch.getText().toUpperCase();	//On recupère le texte entree dans le JtextField					
				for (int i = 0; i< data.size();i++) {
					//Comparaison des elements contenu dans l ArrayList et du texte entree 
					if (data.get(i).toString().indexOf(enteredText) != -1) {
						model.addElement(data.get(i).toString());//ajout de lelement dans le nouveau model
					}
				}					
				liste.setModel(model);//On definit ce nouveau model pour la JList
			}
		});	
	}

	/**
	 * 
	 */
	public void initList() {
		listeModel = new DefaultListModel<DataRefLibelle> ();
		liste = new JList<DataRefLibelle>();
		liste.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			    if (e.getClickCount() == 2){  
			        actionSelectionner();
			    }
			    else{
			    	setEnableButton(true);
			    }
			      
			}
		});
		liste.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				setEnableButton(true);
			}
		});
		liste.setModel(listeModel);
		liste.setBounds(10, 11, 314, 378);
		contentPanel.add(liste);
	}

	/**
	 * 
	 */
	public void initComponents() {
		//setBounds(0, 0, 350, 535);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	}


	public DataRefLibelle showDialog2() {
		
		sendData = false;
		
		super.showDialog();
		
		return sendData ? dataSelected : null;
	}
	
	protected void setVisibleEdit(boolean b){
		btnEditer.setVisible(b);
	}
	protected void setVisibleSelect(boolean b){
		btnSelectionner.setVisible(b);
	}
	protected void setVisibleSupp(boolean b){
		btnSupprimer.setVisible(b);
	}
	protected void setVisibleAjouter(boolean b){
		btnAjouter.setVisible(b);
	}

	
	
	/**
	 * @param b 
	 * 
	 */
	private void setEnableButton(boolean b) {
		btnEditer.setEnabled(b);
		btnSupprimer.setEnabled(b);
		btnSelectionner.setEnabled(b);
	}
	
	public void load()
	{
		data = manager.load(listeModel);
	}

	protected void actionSelectionner() {
		
		sendData = true;
		dataSelected = liste.getSelectedValue();
		setVisible(false);
		
	}

	protected void actionSupprimer() {
		
		
		DataRefLibelle dataSel = liste.getSelectedValue();
		int index = liste.getSelectedIndex();
		listeModel.remove(index);
		
		manager.del(dataSel);
		
	}

	protected void actionEditer() {
		
		AddLibelleView dial  = new AddLibelleView(manager,listeModel);
		
		int index = liste.getSelectedIndex();
		DataRefLibelle dataSel = liste.getSelectedValue();
		dataSel.setIndex(index);
		dial.load(dataSel);
		dial.showDialog();
		
	}

	protected void actionAjouter() {
		AddLibelleView dial  = new AddLibelleView(manager,listeModel);
		dial.showDialog();
	}
}

package model;

import java.util.Vector;

import javax.swing.DefaultListModel;

import controleur.ControlManager;

public abstract class ManagerRefLibelle {
	
	ControlManager controlManager;
	private String title;
	private String lbtxt;
	

	public ManagerRefLibelle(ControlManager controlManager,String title,String lbt) {
		this.controlManager = controlManager;
		this.title = title;
		this.lbtxt=lbt;
		
	}
	public abstract DataRefLibelle  add(String text);
	public abstract void del(DataRefLibelle element);
	public abstract void maj(DataRefLibelle element);
	public abstract Vector<DataRefLibelle> load(DefaultListModel<DataRefLibelle> listeModel);
	public  String getTitle() {
		return title;
	}
	public String getTextLabel() {
		return this.lbtxt;
	}

}

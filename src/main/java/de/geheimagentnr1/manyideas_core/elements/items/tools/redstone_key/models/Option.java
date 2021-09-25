package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models;

public class Option {
	
	
	private final String title;
	
	private final String description;
	
	public Option( String _title, String _description ) {
		
		title = _title;
		description = _description;
	}
	
	public String getTitle() {
		
		return title;
	}
	
	public String getDescription() {
		
		return description;
	}
}

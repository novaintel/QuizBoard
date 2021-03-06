package br.ufscar.dc.thingbroker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event implements Serializable, Comparable<Event>{
	
	private static final long serialVersionUID = 1L;
	
    private String eventId;
	private String thingId;
	private Long serverTimestamp;
	private Object info;
	private List<String> content;
    
    public Event() {
    	content = new ArrayList<String>();
    }
    
    public Event(String eventId) {
    	this.eventId = eventId;
    }
    
    public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public Long getServerTimestamp() {
		return serverTimestamp;
	}
	public void setServerTimestamp(Long serverTimestamp) {
		this.serverTimestamp = serverTimestamp;
	}
    public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}
	public List<String> getContent() {
		return content;
	}
	public void setData(List<String> content) {
		this.content = content;
	}

	public String getThingId() {
		return thingId;
	}

	public void setThingId(String thingId) {
		this.thingId = thingId;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", thingId=" + thingId
				+ ", serverTimestamp=" + serverTimestamp + ", data=" + content
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result
				+ ((serverTimestamp == null) ? 0 : serverTimestamp.hashCode());
		result = prime * result + ((thingId == null) ? 0 : thingId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Event other = (Event) obj;
		if(this.eventId.equals(other.eventId)) {
			return true;
		}
		return false;
	}

	public int compareTo(Event o) {
		return this.serverTimestamp.compareTo(o.serverTimestamp) * -1;
	}
} 

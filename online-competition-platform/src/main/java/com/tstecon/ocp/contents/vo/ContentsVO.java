package com.tstecon.ocp.contents.vo;

import org.springframework.stereotype.Component;

@Component("ContentsVO")
public class ContentsVO {
	private int contents_id;
	private String mem_id;
	private int compet_id;
	private String contents_state;
	private String contents_processing_date;
	private String contents_reject_reason;
	private int contents_view;
	private String contents_name;
	private String contents_text;

	public int getContents_id() {
		return contents_id;
	}

	public void setContents_id(int contents_id) {
		this.contents_id = contents_id;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public int getCompet_id() {
		return compet_id;
	}

	public void setCompet_id(int compet_id) {
		this.compet_id = compet_id;
	}

	public String getContents_state() {
		return contents_state;
	}

	public void setContents_state(String contents_state) {
		this.contents_state = contents_state;
	}

	public String getContents_processing_date() {
		return contents_processing_date;
	}

	public void setContents_processing_date(String contents_processing_date) {
		this.contents_processing_date = contents_processing_date;
	}

	public String getContents_reject_reason() {
		return contents_reject_reason;
	}

	public void setContents_reject_reason(String contents_reject_reason) {
		this.contents_reject_reason = contents_reject_reason;
	}

	public int getContents_view() {
		return contents_view;
	}

	public void setContents_view(int contents_view) {
		this.contents_view = contents_view;
	}

	public String getContents_name() {
		return contents_name;
	}

	public void setContents_name(String contents_name) {
		this.contents_name = contents_name;
	}

	public String getContents_text() {
		return contents_text;
	}

	public void setContents_text(String contents_text) {
		this.contents_text = contents_text;
	}
}

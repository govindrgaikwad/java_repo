package com.socman.data.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.socman.data.base.BaseEntityAttribute;

/**
 * The persistent class for the attachment database table.
 * 
 */
@Entity
@Table(name = "Attachment", schema = "soc")
public class Attachment extends BaseEntityAttribute {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AttachmentID")
	private int attachmentID;

	@Column(name = "AttachmentPath")
	private String attachmentPath;

	// bi-directional many-to-one association to Transaction
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TransactionID")
	private Transaction transaction;

	public Attachment() {
	}

	public int getAttachmentID() {
		return this.attachmentID;
	}

	public void setAttachmentID(int attachmentID) {
		this.attachmentID = attachmentID;
	}

	public String getAttachmentPath() {
		return this.attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public Transaction getTransaction() {
		return this.transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}
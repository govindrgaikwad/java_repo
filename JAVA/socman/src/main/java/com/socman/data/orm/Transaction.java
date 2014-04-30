package com.socman.data.orm;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.socman.data.base.BaseEntityAttribute;

/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity
@Table(name = "Transaction", schema = "soc")
public class Transaction extends BaseEntityAttribute {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TransactionID")
	private Integer transactionID;

	@Column(name = "Amount")
	private Double amount;

	@Column(name = "ChequeNum")
	private String chequeNum;

	@Column(name = "Notes")
	private String notes;

	@Column(name = "PaymentMode")
	private String paymentMode;

	@Column(name = "TranEffectiveDt")
	private Date tranEffectiveDt;

	@Column(name = "TransactionType")
	private String transactionType;

	// bi-directional many-to-one association to Attachment
	@OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY)
	private List<Attachment> attachments;

	// bi-directional many-to-one association to Society
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SocietyID")
	private Society society;

	public Transaction() {
	}

	public Integer getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getChequeNum() {
		return chequeNum;
	}

	public void setChequeNum(String chequeNum) {
		this.chequeNum = chequeNum;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Date getTranEffectiveDt() {
		return tranEffectiveDt;
	}

	public void setTranEffectiveDt(Date tranEffectiveDt) {
		this.tranEffectiveDt = tranEffectiveDt;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public Society getSociety() {
		return society;
	}

	public void setSociety(Society society) {
		this.society = society;
	}

	public Attachment addAttachment(Attachment attachment) {
		getAttachments().add(attachment);
		attachment.setTransaction(this);

		return attachment;
	}

	public Attachment removeAttachment(Attachment attachment) {
		getAttachments().remove(attachment);
		attachment.setTransaction(null);

		return attachment;
	}

}
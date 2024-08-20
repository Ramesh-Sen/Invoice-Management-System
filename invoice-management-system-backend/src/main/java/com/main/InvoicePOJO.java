package com.main;

public class InvoicePOJO {
	private String businessCode;
	private String custNumber;
	private String nameCustomer;
	private String clearDate;
	private Integer businessYear;
	private Long docId;
	private String postingDate;
	private String documentCreateDate;
	private String documentCreateDate1;
	private String dueInDate;
	private String invoiceCurrency;
	private String documentType;
	private Integer postingId;
	private String areaBusiness;
	private Double totalOpenAmount;
	private String baselineCreateDate;
	private String custPaymentTerms;
	private Long invoiceId;
	private Integer isOpen;
	private String notes;

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		nameCustomer = nameCustomer.replace("'", "''");
		this.nameCustomer = nameCustomer;
	}

	public String getClearDate() {
		return clearDate;
	}

	public void setClearDate(String clearDate) {
		if (clearDate.isEmpty())
			clearDate = "NULL";
		this.clearDate = clearDate;
	}

	public Integer getBusinessYear() {
		return businessYear;
	}

	public void setBusinessYear(String businessYear) {
		try {
			businessYear = businessYear.replace(".0", "");
			this.businessYear = Integer.parseInt(businessYear);
		} catch (Exception e) {
			this.businessYear = null;
		}
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		try {
			docId = docId.replace(".0", "");
			this.docId = Long.parseLong(docId);
		} catch (Exception e) {
			this.docId = null;
		}
	}

	public String getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}

	public String getDocumentCreateDate() {
		return documentCreateDate;
	}

	public void setDocumentCreateDate(String documentCreateDate) {
		if (documentCreateDate.length() == 8)
			documentCreateDate = documentCreateDate.substring(0, 4) + '-' + documentCreateDate.substring(4, 6) + '-'
					+ documentCreateDate.substring(6, 8);
		this.documentCreateDate = documentCreateDate;
	}

	public String getDocumentCreateDate1() {
		return documentCreateDate1;
	}

	public void setDocumentCreateDate1(String documentCreateDate1) {
		if (documentCreateDate1.length() == 8)
			documentCreateDate1 = documentCreateDate1.substring(0, 4) + '-' + documentCreateDate1.substring(4, 6) + '-'
					+ documentCreateDate1.substring(6, 8);
		this.documentCreateDate1 = documentCreateDate1;
	}

	public String getDueInDate() {
		return dueInDate;
	}

	public void setDueInDate(String dueInDate) {

		dueInDate = dueInDate.replace(".0", "");
		if (dueInDate.length() == 8)
			dueInDate = dueInDate.substring(0, 4) + '-' + dueInDate.substring(4, 6) + '-' + dueInDate.substring(6, 8);

		this.dueInDate = dueInDate;
	}

	public String getInvoiceCurrency() {
		return invoiceCurrency;
	}

	public void setInvoiceCurrency(String invoiceCurrency) {
		this.invoiceCurrency = invoiceCurrency;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public Integer getPostingId() {
		return postingId;
	}

	public void setPostingId(String postingId) {
		try {
			postingId = postingId.replace(".0", "");
			this.postingId = Integer.parseInt(postingId);
		} catch (Exception e) {
			this.postingId = null;
		}
	}

	public String getAreaBusiness() {
		return areaBusiness;
	}

	public void setAreaBusiness(String areaBusiness) {
		if (areaBusiness.isEmpty())
			areaBusiness = null;
		this.areaBusiness = areaBusiness;
	}

	public Double getTotalOpenAmount() {
		return totalOpenAmount;
	}

	public void setTotalOpenAmount(String totalOpenAmount) {
		try {
			this.totalOpenAmount = Double.parseDouble(totalOpenAmount);
		} catch (Exception e) {
			this.totalOpenAmount = null;
		}
	}

	public String getBaselineCreateDate() {
		return baselineCreateDate;
	}

	public void setBaselineCreateDate(String baselineCreateDate) {
		if (baselineCreateDate.length() == 8)
			baselineCreateDate = baselineCreateDate.replace(".0", "");
		baselineCreateDate = baselineCreateDate.substring(0, 4) + '-' + baselineCreateDate.substring(4, 6) + '-'
				+ baselineCreateDate.substring(7, 8);

		this.baselineCreateDate = baselineCreateDate;
	}

	public String getCustPaymentTerms() {
		return custPaymentTerms;
	}

	public void setCustPaymentTerms(String custPaymentTerms) {
		this.custPaymentTerms = custPaymentTerms;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		try {
			invoiceId = invoiceId.replace(".0", "");
			this.invoiceId = Long.parseLong(invoiceId);
		} catch (Exception e) {
			this.invoiceId = null;
		}
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		try {
			this.isOpen = Integer.parseInt(isOpen);
		} catch (Exception e) {
			this.isOpen = null;
		}
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	InvoicePOJO() {
		businessCode = null;
		custNumber = null;
		nameCustomer = null;
		clearDate = null;
		businessYear = null;
		docId = null;
		postingDate = null;
		documentCreateDate = null;
		documentCreateDate1 = null;
		dueInDate = null;
		invoiceCurrency = null;
		documentType = null;
		postingId = null;
		areaBusiness = null;
		totalOpenAmount = null;
		baselineCreateDate = null;
		custPaymentTerms = null;
		invoiceId = null;
		isOpen = null;
		notes = null;
	}
}

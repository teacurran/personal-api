package com.wirelust.personalapi.client.fitbit.representations;

/**
 * Date: 1/21/15
 *
 * @author T. Curran
 */
public class WaterType {

	Long logId;
	Double amount;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}
}

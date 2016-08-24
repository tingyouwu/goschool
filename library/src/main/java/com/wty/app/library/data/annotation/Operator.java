package com.wty.app.library.data.annotation;

/**
 * @author wty
 * 用于缓存对象模型
 **/
public enum Operator {
	/** EQUALS ('=') */
	eq("="),
	/** NOT EQUALS ('&gt;&lt;') */
	neq("<>"),
	/** IS */
	is(" IS "),
	/** IS NOT */
	isNot(" IS NOT "),
	/** GREATER THAN ('&gt;') */
	gt(">"),
	/** LESS THAN ('&lt;') */
	lt("<"),
	/** GREATER THAN OR EQUAL ('&gt;=') */
	gte(">="),
	/** LESS THAN OR EQUAL ('&lt;=') */
	lte("<="),
	/** AND */
	and(" AND "),
	/** OR */
	or(" OR "),
	/** NOT */
	not(" NOT "),
	/** EXISTS */
	exists(" EXISTS "),
	/** LIKE */
	like(" LIKE "),
	/** NOT LIKE */
	notLike(" NOT LIKE "),
	/** IN */
	in(" IN "),
	/** NOT IN */
	notIn(" NOT IN "),
	/** BETWEEN */
	between(" BETWEEN "),
	/** NOT BETWEEN */
	notBetween(" NOT BETWEEN "),
	/** GLOB */
	glob(" GLOB "),
	/** NOT GLOB */
	notGlob(" NOT GLOB "),
	/** MATCH */
	match(" MATCH ");

	public final String operator;

	Operator(String operator) {
		this.operator = operator;
	}
}

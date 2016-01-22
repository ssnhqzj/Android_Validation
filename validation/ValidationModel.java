/**
 *  Copyright 2014 ken.cai (http://www.shangpuyun.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 *
 */
package com.qzj.logistics.validation;

import android.widget.EditText;
import android.widget.TextView;

/**
 * 校验模型
 * 里边存放需要验证的EditText和验证器
 */
public class ValidationModel {

	// editText名称
	private String modelName;

	// relateEditText名称
	private String relateModelName;

	private EditText editText;

	// 关联的EditText，用来和前一个EditText对比验证
	private EditText relateEditText;

	// 验证失败后显示提示信息的View
	private TextView hintView;

	// 验证器
	private ValidationExecutor validationExecutor;

	// 存放验证结果是否成功
	private boolean validationSuccess;

	public ValidationModel(String modelName,EditText editText, TextView hintView,ValidationExecutor validationExecutor) {
		this(modelName,editText,null,hintView,validationExecutor);
	}

	public ValidationModel(String modelName,EditText editText, EditText relateEditText, TextView hintView, ValidationExecutor validationExecutor) {
		this.modelName = modelName;
		this.editText = editText;
		this.relateEditText = relateEditText;
		this.hintView = hintView;
		this.validationExecutor = validationExecutor;
	}
	
	public EditText getEditText() {
		return editText;
	}

	public ValidationModel setEditText(EditText editText) {
		this.editText = editText;
		return this;
	}

	public ValidationExecutor getValidationExecutor() {
		return validationExecutor;
	}

	public ValidationModel setValidationExecutor(ValidationExecutor validationExecutor) {
		this.validationExecutor = validationExecutor;
		return this;
	}

	public EditText getRelateEditText() {
		return relateEditText;
	}

	public void setRelateEditText(EditText relateEditText) {
		this.relateEditText = relateEditText;
	}

	public boolean isValidationSuccess() {
		return validationSuccess;
	}

	public void setValidationSuccess(boolean validationSuccess) {
		this.validationSuccess = validationSuccess;
	}

	public TextView getHintView() {
		return hintView;
	}

	public void setHintView(TextView hintView) {
		this.hintView = hintView;
	}

	public String getModelName() {
		if(modelName == null)
			modelName = "";

		return modelName;
	}

	public ValidationModel setModelName(String modelName) {
		this.modelName = modelName;
		return this;
	}

	public String getRelateModelName() {
		if(relateModelName == null)
			relateModelName = "";

		return relateModelName;
	}

	public ValidationModel setRelateModelName(String relateModelName) {
		this.relateModelName = relateModelName;
		return this;
	}

	/**
	 * 两个EditText是否需要关联验证
	 * @return
	 */
	public boolean isNeedRelate() {
		if(relateEditText != null){
			return true;
		}

		return false;
	}

}

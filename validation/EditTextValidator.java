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

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import java.util.ArrayList;

/**
 * EditText校验器
 * 
 */
public class EditTextValidator {

	private ArrayList<ValidationModel> validationModels;

	private View button;

	private Context context;

	private boolean isSuccess;

	// 验证未通过信息
	private String failMsg;

	private OnRelationBtnStateListener relationListener;

	public EditTextValidator(Context context) {
		this(context, null);
	}

	public EditTextValidator(Context context, View button) {
		this.context = context;
		this.button = button;
		validationModels = new ArrayList<ValidationModel>();
	}

	/**
	 * 添加验证模型
	 * @param validationModel
	 * @return
	 */
	public EditTextValidator add(ValidationModel validationModel) {
		validationModels.add(validationModel);
		return this;
	}

	/**
	 * 清除验证器
	 */
	public void clear(){
		validationModels.clear();
	}

	/**
	 * 执行验证
	 * @return
	 */
	public void execute() {
		for (final ValidationModel validationModel : validationModels) {
			if(validationModel.isNeedRelate()){
				validationModel.getRelateEditText().addTextChangedListener(new EditTextWatcher(validationModel));
			}

			validationModel.getEditText().addTextChangedListener(new EditTextWatcher(validationModel));
		}

		setEnabled();
	}

	/**
	 * 执行验证--一次执行
	 * @return
	 */
	public boolean executeOnce() {
		for (final ValidationModel validationModel : validationModels) {
			if (!validate(validationModel)){
				failMsg = validationModel.getValidationExecutor().getHintMsg();
				return false;
			}
		}

		return true;
	}

	/**
	 * 校验
	 * @return
	 */
	private boolean validate(ValidationModel model) {
		// 如果没有验证处理器，直接返回正确
		if(model.getValidationExecutor() == null)
			return true;

		// 两个EditText关联验证
		if(model.isNeedRelate()){
			return model.getValidationExecutor().doValidate(context, model);
		}else{
			return model.getValidationExecutor().doValidate(context, model);
		}
	}

	/**
	 * 验证除当前model以外的其他model
	 * @param excludeModel
	 */
	private void validateOther(ValidationModel excludeModel){
		for(ValidationModel model : validationModels){
			if(model == excludeModel) continue;
			if(!validate(model)) return;
		}
	}

	/**
	 * 设置关联按钮的是否可以点击
	 */
	private void setEnabled() {
		if (button == null)
			return;

		for (final ValidationModel validationModel : validationModels) {
			if (!validationModel.isValidationSuccess()) {
				button.setEnabled(false);
				if (relationListener != null)
					relationListener.onStateChange(false);
				return;
			}
		}

		if (!button.isEnabled()) {
			button.setEnabled(true);
			if (relationListener != null)
				relationListener.onStateChange(true);
		}
		isSuccess = true;
	}

	/**
	 *
	 * 设置button，支持各种有点击事件的view
	 *
	 * @param button
	 * @return
	 */
	public EditTextValidator setButton(View button) {
		this.button = button;
		return this;
	}

	public String getFailMsg() {
		return failMsg;
	}

	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
	}

	public View getButton() {
		return button;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setRelationListener(OnRelationBtnStateListener relationListener) {
		this.relationListener = relationListener;
	}

	/**
	 * 关联button的状态改变监听
	 */
	public interface OnRelationBtnStateListener {
		public void onStateChange(boolean isEnable);
	}

	class EditTextWatcher implements TextWatcher {

		private ValidationModel validationModel;

		public EditTextWatcher(ValidationModel validationModel){
			this.validationModel = validationModel;
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			validateOther(validationModel);
			validationModel.setValidationSuccess(validate(validationModel));
			setEnabled();
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			// 改变前先清除提示文字
			if (validationModel.getHintView() != null)
				validationModel.getHintView().setText("");
		}

		@Override
		public void afterTextChanged(Editable s) {

		}
	}

}

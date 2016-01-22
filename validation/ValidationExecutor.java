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

import com.qzj.logistics.validation.utils.ValidationUtil;


/**
 * 
 * 校验执行者
 * 
 */
public abstract class ValidationExecutor extends ValidationUtil {

	/**
	 * 提示信息
	 */
	protected String hintMsg;

	/**
	 * 这里做校验处理
	 * @param context
	 * @param model 验证的model
	 * @return 校验成功返回true 否则返回false
	 */
	public abstract boolean doValidate(Context context, ValidationModel model);

	public String getHintMsg() {
		return hintMsg;
	}
}

package com.example.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/*private static final String ERROR = "common/error";
	
		@ExceptionHandler(Exception.class)
		public Object defualtErrorHandler(HttpServletRequest req, Exception e) {
			if (isAjax(req)) {
				return jsonErrorHandler(req, e);
			}
			log.error(e.getMessage());
			return ERROR;
		}
	*/
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> jsonErrorHandler(HttpServletRequest req, Exception e) {
		/*String code = getCode(e);
		HttpStatus status = null;
		if (!"1".equals(code)) {
			status = HttpStatus.OK;
		} else {
			status = getStatus(req);
			log.error(e.getMessage());
		}
		Map<String, Object> body = getBody(code);*/
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("message", e.getMessage());
		HttpStatus status = getStatus(req);
		log.error(e.getMessage());
		return new ResponseEntity<Map<String, Object>>(body, status);
	}

	/*private Map<String, Object> getBody(String code) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		return map;
	}
	
	public String getCode(Exception e) {
		if (e instanceof BusinessException) {
			BusinessException be = (BusinessException) e;
			return be.getCode();
		} else if (e instanceof UploadException) {
			UploadException ue = (UploadException) e;
			return ue.getCode();
		} else if (e instanceof CSVImportException) {
			CSVImportException ue = (CSVImportException) e;
			return ue.getCode();
		} else if (e instanceof CSVFormatException) {
			CSVFormatException ue = (CSVFormatException) e;
			return ue.getCode();
		}
		return "1";
	}
	
	private boolean isAjax(HttpServletRequest req) {
		String requestType = req.getHeader("X-Requested-With");
		if (StringUtils.isNotBlank(requestType)) {
			return true;
		}
		return false;
	}*/

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}

		try {
			return HttpStatus.valueOf(statusCode);
		} catch (Exception ex) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}

}

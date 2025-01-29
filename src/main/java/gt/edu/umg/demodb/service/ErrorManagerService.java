package gt.edu.umg.demodb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import gt.edu.umg.demodb.utils.AppProperty;
import gt.edu.umg.demodb.utils.ResponseResult;

@Service
public class ErrorManagerService {

	private boolean showException;
	private static final Logger logger = LoggerFactory.getLogger(ErrorManagerService.class);

	public ErrorManagerService(AppProperty properties) {
		this.showException = properties.getShowException() == 1;
	}

	public String managerException(Exception e) {
		if (this.showException) {
			if (e.getMessage() == null) {
				return "Excepcion por valor nulo";
			}
			return e.getMessage();
		}
		String tmpMessage = ResponseResult.fail.getMessage();
		String message = e.getMessage();
		if (message == null) {
			tmpMessage = "Los datos indicados no son validos, favor de verificar";
		} else {
			message = message.toLowerCase();
			if (message.contains("no value present")) {
				tmpMessage = "Hay incrongruencia con los datos, verifique que todas las pantallas contengan la información completa. Si el error persiste, favor de indicar al administrador del sistema";
			} else if (message.contains("duplicate key value") || message.contains("llave duplicada")) {
				tmpMessage = "La información que intenta agregar ya se encuentra registrada, refresque y verifique";
			}
		}
		message += " -> " + e.getStackTrace()[0].getMethodName();
		logger.info(message);
		return tmpMessage;
	}

}

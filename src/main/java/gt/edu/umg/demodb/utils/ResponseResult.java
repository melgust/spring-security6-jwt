package gt.edu.umg.demodb.utils;

public enum ResponseResult {

    fail(-1), success(1), warning(0), notfound(-4);
    private final int value;

    private ResponseResult(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public String getMessage() {
        String msg;
        switch(value) {
            case 1:
                msg = "Proceso exitoso";
                break;
            case -1:
                msg = "Hay errores que debe verificar";
                break;
            case 0:
                msg = "Precaución, revise el proceso";
                break;
            case -4:
                msg = "No hay registros relacionados";
                break;
            default:
                msg = "Error al procesar, intentelo de nuevo";
                break;
        }
        return msg;
    }
}
package cn.sdut.entity;

/**
 * 本类是前后端分离相互调用的桥梁
 * 前端调用后端接口  返回的数据
 * @author 赵德锋
 */
public class Result {

    // 成功或者失败
    private boolean success;
    // 提示消息
    private String message;
    // 总记录数
    private Long total;
    // 表示数据
    private Object data;

    // 增删改
    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // 查询全部数据
    public Result(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    //查询数据并且返回数据条数
    public Result(boolean success, String message, Long total, Object data) {
        this.success = success;
        this.message = message;
        this.total = total;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", total=" + total +
                ", data=" + data +
                '}';
    }
}

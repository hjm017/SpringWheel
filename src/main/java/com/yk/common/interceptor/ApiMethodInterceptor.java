package com.yk.common.interceptor;

import org.aopalliance.intercept.Interceptor;

/**
 * @author hjm
 * @Time 2016/5/2 10:56.
 */
public class ApiMethodInterceptor implements Interceptor {

   /* private static String    modelPackPath = "com.carme.api.dto";

    private static final Log reqLog        = LogFactory.getLog("rest.request");
    private static final Log errLog        = LogFactory.getLog("rest");

    @Autowired
    private CommonComponent  commonComponent;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //获得threadlocal的接口信息值
        ApiRequest request = AuthUtil.getRequest();
        StringBuilder logTrace = new StringBuilder();
        //打印前置日志
        if (PropComponent.getProp().isRequestSwitch()) {
            logTrace.append(RequestUtils.getRequestMsg(request));
        }
        Object result = execute(request, invocation);
        //打印返回结果
        if (PropComponent.getProp().isRequestSwitch() && result != null) {
            logTrace.append("\nresult:").append(JsonUtil.toJson(result));
            reqLog.info(logTrace.toString());
        }
        return result;
    }

    *//**
     * 检查参数
     * @param invocation
     * @return
     * @throws Throwable
     *//*
    private Object execute(ApiRequest reqInfo, MethodInvocation invocation) throws Throwable {

        BaseParamDTO param = (BaseParamDTO) reqInfo.getParam();
        //检查输入参数合法
        checkParam(param);
        //检查权限
        Object authError = checkAuth(invocation, reqInfo);
        if (authError != null) {
            return authError;
        }
        //检查注释参数
        Object paramError = checkParamAnno(param, invocation);
        if (paramError != null) {
            return paramError;
        }

        Object result = null;
        try {
            result = invocation.proceed();
        } catch (Exception e) {
            //异常拦截
            ApiRespDTO<?> entity = getErrorResult(invocation, CodeConstants.SYS_SYSTEM_ERROR);
            //打印错误日志
            errLog.error(RequestUtils.getRequestMsg(reqInfo) + "/n" + e.getMessage(), e);
            return entity;
        }
        //检查返回结果合法
        checkResult(result);
        return result;
    }

    *//**
     * 安全验证
     * @param invocation
     * @param reqInfo
     * @return
     * @throws Exception
     *//*
    private ApiRespDTO<?> checkAuth(MethodInvocation invocation, ApiRequest reqInfo)
            throws Exception {
        String callUrl = reqInfo.getUrl();
        for (String authUrl : EnvConstants.AUTH_URL_LIST) {
            if (callUrl.indexOf(authUrl) != -1) {
                BaseParamDTO param = (BaseParamDTO) reqInfo.getParam();
                String token = param.getUserToken();
                if (StringUtils.isBlank(token)) {
                    return getErrorResult(invocation, CodeConstants.SYS_TOKEN_AUTH_ERROR, "token为空");
                }
                PUser user = commonComponent.getUserByToken(token);
                if (user == null) {
                    return getErrorResult(invocation, CodeConstants.SYS_TOKEN_AUTH_ERROR,
                            "token过期或者错误");
                }
                reqInfo.setUserId(user.getId());
                reqInfo.setUsername(user.getLoginName());
                reqInfo.setUserMobile(user.getMobile());
            }
        }
        return null;
    }

    *//**
     * 检查注释参数
     * @param invocation
     * @throws IllegalAccessException
     * @throws InstantiationException
     *//*
    private Object checkParamAnno(BaseParamDTO param, MethodInvocation invocation) throws Exception {
        if (param == null) {
            return null;
        }
        //验证参数
        ValidateResult validate = checkAnno(param);
        if (!validate.isSuccess()) {
            List<String> codeList = validate.getCodeList();
            if (codeList == null || codeList.size() == 0) {
                return getErrorResult(invocation, CodeConstants.SYS_PARAM_CHECK_ERROR,
                        validate.getErrorMsg());
            } else {
                String code = codeList.get(0);
                return getErrorResult(invocation, code, CodeConstants.getCodeMsg(code));
            }
        }

        return null;
    }

    private ValidateResult checkAnno(Object param) {
        ValidateResult result = new ValidateResult();
        result.setSuccess(true);
        //检查注释参数
        if (ValidateProcess.hasAnnotation(param.getClass())) {
            return ValidateProcess.validateObject(param);
        }
        return result;
    }

    *//**
     * 检查是否是model对象
     * @param param
     * @return
     *//*
    public static boolean isParamModel(Object param) {
        if (param == null) {
            return false;
        }
        String paramPackName = param.getClass().getPackage().getName();

        if (paramPackName.indexOf(modelPackPath) != -1) {
            return true;
        } else {
            return false;
        }
    }

    private void checkResult(Object object) {
        if (object == null) {
            return;
        }
        if (!(object instanceof ApiRespDTO)) {
            throw new RuntimeException("返回结果必须为ApiResultDTO<T>");
        }
    }

    private void checkParam(Object object) {
        if (object == null) {
            return;
        }
        if (!(object instanceof BaseParamDTO)) {
            throw new RuntimeException("输入参数必须继承BaseParamDTO");
        }
    }

    private ApiRespDTO<?> getErrorResult(MethodInvocation invocation, String code) throws Exception {
        return getErrorResult(invocation, code, CodeConstants.getCodeMsg(code));
    }

    private ApiRespDTO<?> getErrorResult(MethodInvocation invocation, String code, String message)
            throws Exception {
        ApiRespDTO<?> result = new ApiRespDTO<Object>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }*/
}

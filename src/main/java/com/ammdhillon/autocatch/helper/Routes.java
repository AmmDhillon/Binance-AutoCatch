package com.ammdhillon.autocatch.helper;

public class Routes {

    /**
     * API VERSION
     */
    public static final String API_VERSION = "/api/v1";

    /**
     * ACCESS TYPE
     */
    public static final String ADMIN = API_VERSION + "/admin";

    /**
     * ADMIN ROUTE GROUP
     */
    public static final String WALLET = ADMIN + "/wallet";
    public static final String COMMISSION = ADMIN + "/commission";
    public static final String SERVICE = ADMIN + "/service";
    public static final String SETTING = ADMIN + "/setting";
    public static final String ACTIVITY = ADMIN + "/activity";
    public static final String TICKET = ADMIN + "/ticket";
    public static final String MEMBER = ADMIN + "/member";
    public static final String API = ADMIN + "/api";
    public static final String NOTIFICATION = ADMIN + "/notification";

    public static final String ACTIONS = "/actions";
    public static final String APIS = "/apis";

    /**
     * WALLET GROUP ENDPOINTS
     */
    public static final String ALL_PARAMS = "/allParams";
    public static final String MANAGE_BALANCE = "/manageBalance";
    public static final String USER_WALLET_INFO = "/userWalletInfo";

    /**
     * SETTING GROUP ENDPOINTS
     */
    public static final String SET_SETTING = "/setSetting";
    public static final String GET_SETTING = "/getSetting";

    /**
     * ACTIVITY GROUP ENDPOINTS
     */
    public static final String GET_ALL_ACTIVITY = "/all";
    public static final String GET_USER_HISTORY = "/history";
    public static final String GET_USER_ACTIVITY = "/userActivity";
    public static final String GET_USER_TRANSACTION_PURPOSE_ACTIVITY = "/userPurposeActivity";
    public static final String GET_TRANSACTION_PURPOSE_ACTIVITY = "/purposeActivity";

    /**
     * COMMISSION GROUP ENDPOINTS
     */
    public static final String GET_ALL_COMMISSIONS = "/all";
    public static final String GET_COMMISSION = "/get";
    public static final String ADD_COMMISSION = "/add";
    public static final String UPDATE_COMMISSION = "/update";
    public static final String DELETE_COMMISSION = "/delete";
    public static final String UPDATE_ORDER = "/updateOrder";

    /**
     * TICKET GROUP ENDPOINTS
     */
    public static final String GET_ALL_TICKETS = "/all";
    public static final String GET_TICKET_CHAT = "/chat";
    public static final String UPDATE_TICKET_STATUS = "/updateStatus";
    public static final String REPLY = "/reply";

    /**
     * MEMBER GROUP ENDPOINTS
     */
    public static final String GET_ALL_MEMBERS = "/all";
    public static final String GET_MEMBER_DETAIL = "/detail";
    public static final String GET_MEMBERS_UNDER_WATCH = "/underWatch";
    public static final String UPDATE_MEMBER_STATUS = "/updateStatus";
    public static final String UPDATE_MEMBER_WATCHLIST = "/updateWatchlist";
    public static final String CHANGE_PASSWORD_ADMIN = "/changePassword";

    /**
     * API GROUP ENDPOINTS
     */
    public static final String GET_ALL_API = "/all";
    public static final String ADD_API = "/add";
    public static final String DELETE_API = "/delete";
    public static final String UPDATE_API = "/update";

    /**
     * SERVICE GROUP ENDPOINTS
     */
    public static final String GET_ALL_SERVICES = "/all";
    public static final String ADD_SERVICE = "/add";
    public static final String DELETE_SERVICE = "/delete";

    /**
     * NOTIFICATION GROUP ENDPOINTS
     */
    public static final String GET_ALL_NOTIFICATIONS = "/all";
    public static final String CREATE_NEW_NOTIFICATION = "/create";

    // ------------------------------------------------------------------------------ //

    /**
     * USER ROUTE GROUPS
     */
    public static final String USER = API_VERSION + "/user";
    public static final String DETAIL = API_VERSION + "/detail";
    public static final String RECHARGE = API_VERSION + "/recharge";
    public static final String CONVERSATION = API_VERSION + "/conversation";
    public static final String DATA = API_VERSION + "/data";
    public static final String NOTIFICATION_USER = API_VERSION + "/notification";

    /**
     * USER GROUP ENDPOINTS
     */
    public static final String SIGN_UP = "/signUp";
    public static final String LOGIN = "/login";
    public static final String CHANGE_PASSWORD = "/changePassword";
    public static final String CHECK_USER_STATUS = "/checkUserStatus";
    public static final String INFO = "/info";

    /**
     * DETAIL GROUP ENDPOINTS
     */
    public static final String GET_BANNERS = "/banners";
    public static final String GET_RECHARGE_DETAILS = "/recharge";
    public static final String GET_OPERATORS = "/operators";
    public static final String GET_WALLET_DETAILS = "/wallet";
    public static final String GET_ACCOUNT_DETAILS = "/account";
    public static final String GET_HISTORY = "/history";

    /**
     * RECHARGE GROUP ENDPOINTS
     */
    public static final String MOBILE = "/mobile";
    public static final String DTH = "/dth";
    public static final String ELECTRICITY = "/electricity";
    public static final String ADD_MONEY = "/addMoney";
    public static final String USER_RECHARGE_HISTORY = "/rechargeHistory";

    /**
     * CONVERSATION GROUP ENDPOINTS
     */
    public static final String GET_USER_CONVERSATIONS = "/all";
    public static final String GET_CHAT = "/chat";
    public static final String NEW_QUERY = "/newQuery";
    public static final String NEW_MESSAGE = "/newMessage";
    public static final String UPDATE_STATUS = "/updateStatus";

    /**
     * NOTIFICATION GROUP ENDPOINTS
     */
    public static final String GET_USER_NOTIFICATIONS = "/all";

    /**
     * DATA GROUP ENDPOINTS
     */
    public static final String GET_CIRCLES = "/circles";
    public static final String GET_SERVICE = "/services";

    public static final String SWAP_REQUEST = "/swapRequest";
    public static final String REQUEST_CODE = "/requestCode";
    public static final String VERIFY_CODE = "/verifyCode";
    public static final String UPDATE_DATA = "/update";
    public static final String INIT_SERVICE = "/init";
    public static final String GET_DATA = "/get";
    public static final String GET_TOPICS = "/topics";
    public static final String GET_TRANSACTIONS = "/transactions";
    public static final String GET_TOKENS = "/tokens";
    public static final String GET_BALANCES = "/balances";
    public static final String UPDATE_TOKEN = "/updateToken";
    public static final String CREATE_CHANNEL = "/createChannel";
    public static final String GET_ALL_CHATS = "/getAllChats";
    public static final String GET_FULL_CHAT = "/getFullChat";
    public static final String SEND_TRANSACTION = "/sendTransaction";
}

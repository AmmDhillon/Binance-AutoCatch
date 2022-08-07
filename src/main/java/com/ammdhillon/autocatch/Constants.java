package com.ammdhillon.autocatch;

public class Constants {

    public static final String SUCCESS = "Success";
    public static final String FAILED = "Failed";

    public static final String SOMETHING_WRONG = "Something unexpected has happened";

    public static final String ALREADY_EXISTS = "Phone number already exists";
    public static final String APPROVAL_PENDING = "Your account will be activated after checking, please be patient";
    public static final String USES_AUTH_ERROR = "Number/Password is wrong";
    public static final String PENDING = "It has been processed, status will be updated shortly.";
    public static final String BANNED_MSG = "Your have been barred from using ZPay. Please contact the Support";
    public static final String UNAUTHORIZED_ACCESS = "Unauthorized access";
    public static final String SIGN_UP_ERROR = "Something went wrong during sign up";
    public static final String WRONG_BODY = "Request body is not acceptable";
    public static final String DUPLICATE_ERROR = "Similar entry already exists";
    public static final String WRONG_OPERATION = "Wrong operation";
    public static final String USER_DOESNT_EXIST = "User doesn't exist";
    public static final String NOT_ENOUGH_BALANCE = "Not enough balance";
    public static final String USER_NOT_ENOUGH_BALANCE = "User: Not enough balance";
    public static final String ADMIN_NOT_ENOUGH_BALANCE = "Admin: Not enough balance";
    public static final String MOBILE_NUM_ERROR = "Mobile number seems incorrect";
    public static final String WRONG_PARAM = "Request parameter is not acceptable";
    public static final String REQUEST_RECEIVED = "%s | %s Request Received on: %s with data: %s";
    public static final String REQUEST_RECEIVED_DATA = "Request Received: ";
    public static final String OPERATION_SUCCESSFUL = "Operation successful";
    public static final String OPERATION_FAILED = "Operation failed";
    public static final String QUERY_CREATED = "New query created";
    public static final String MESSAGE_SENT = "Message sent";
    public static final String STATUS_UPDATED = "Status updated";
    public static final String COMMISSION_ADDED = "Commission added";
    public static final String COMMISSION_UPDATED = "Commission updated";

    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String COUNTRY_CODE = "country_code";
    public static final String MOBILE_NUM = "mobile_num";
    public static final String TICKET_STATUS = "ticket_status";
    public static final String STATUS = "status";
    public static final String PASSWORD = "password";
    public static final String OTP_TOKEN = "otp_token";
    public static final String REFERRED_BY = "referred_by";

    public static final String SERVICE_TYPE = "service_type";
    public static final String TRANSACTION_PURPOSE = "transaction_purpose";
    public static final String CIRCLE = "circle";

    public static final String RECHARGE_TYPE = "recharge_type";
    public static final String PAGE = "page";
    public static final String LIMIT = "limit";

    public static final String FROM = "from";
    public static final String TO = "to";

    public static final String ADD = "add";
    public static final String DEDUCT = "deduct";
    public static final String SELF = "self";
    public static final String USER = "user";

    public static final String MOBILE = "mobile";
    public static final String DTH = "dth";
    public static final String ELECTRICITY = "electricity";
    public static final String WATER = "recharge";
    public static final String WALLET = "wallet";

    public static final String OPERATOR_CODE = "operator_code";
    public static final String AMOUNT = "amount";

    public static final String QUERY_ID = "query_id";

    public static final String ACCESS_TOKEN = "accessToken";
    public static final String REFRESH_TOKEN = "refreshToken";

    public static final String BSC_SCAN = "bscscan";
    public static final String ETHER_SCAN = "etherscan";
    public static final String POLY_SCAN = "polygonscan";

    public static final String MODULE = "module";
    public static final String ACTION = "action";
    public static final String TX_HASH = "txhash";
    public static final String ADDRESS = "address";
    public static final String TAG = "tag";
    public static final String CONTRACT_ADDRESS = "contractaddress";
    public static final String API_KEY = "apikey";

    public static final String SYMBOL = "symbol";

    public static final String BUY_TOKEN = "buyToken";
    public static final String SELL_TOKEN = "sellToken";
    public static final String SELL_AMOUNT = "sellAmount";
    public static final String BUY_AMOUNT = "buyAmount";
    public static final String SLIPPAGE_PERCENTAGE = "slippagePercentage";
    public static final String IS_TEST = "isTest";

    public static final Long WEI = 1000000000000000000L;
    public static final Long GWEI = 1000000000L;

    // Binance URLs to fetch the newly listed coins
    public static final String ANNOUNCEMENT_URL = "https://www.binance.com/en/support/announcement/c-48";
    public static final String ANNOUNCEMENT_TOKEN_URL = "https://www.binance.com/en/support/announcement/";

    /**
     * These are all dummy URLs
     * Please use your own URLs for these
     */

    // Transaction Signing Nodes
    public static final String MAIN_NODE_ETH = "https://eth-mainnet.alchemyapi.io/v2/XeLfF82wst6jb0fgergr45fwVSpgCi";
    public static final String TEST_NODE_ETH = "https://eth-goerli.alchemyapi.io/v2/3456gfgasfsdfddfgfdjghfugf8fgfg";

    public static final String MAIN_NODE_BSC = "https://speedy-nodes-nyc.moralis.io/7sdhfudsf7sdfhsddf/bsc/mainnet::56";
    public static final String TEST_NODE_BSC = "https://speedy-nodes-nyc.moralis.io/45677usdfghdsfdfshf/bsc/testnet::97";

    public static final String MAIN_NODE_POLYGON = "https://speedy-nodes-nyc.moralis.io/489sduifguis3467fsdu/polygon/mainnet::137";
    public static final String TEST_NODE_POLYGON = "https://speedy-nodes-nyc.moralis.io/47uisdfgsdhufijhsdsdj/polygon/mumbai::80001";

    // BlockChain Ledger API base URLs
    public static final String ETH_URI = "https://api.etherscan.io/api";
    public static final String BSC_URI = "https://api.bscscan.com/api";
    public static final String POLY_URI = "https://api.polygonscan.com/api";

    // BlockChain Ledger API tokens
    public static final String ETH_KEY = "W2PYYVdfsdfsdfsdfsdfsdYJW4ZCHY988";
    public static final String BSC_KEY = "2N1Tdfgdfgfdgdfgdfgfdg5ZBKUET8DTW5";
    public static final String POLY_KEY = "CPZW3AdfgdfgdfgfdgdfgdfgdfXHRXGF4Q";

    // 0x API for quote generation
    public static final String ETH_URI_0X = "https://api.0x.org/swap/v1/quote";
    public static final String BSC_URI_0X = "https://bsc.api.0x.org/swap/v1/quote";
    public static final String POLY_URI_0X = "https://polygon.api.0x.org/swap/v1/quote";
}
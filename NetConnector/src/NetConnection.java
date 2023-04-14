public interface NetConnection {
    /**
     * Method opens the connection to a server provided by TCP/IP protocol
     * at the host address and port number.
     *
     * @param host IP address or computer name
     * @param port Port number
     */
    public void connect(String host, int port);
}
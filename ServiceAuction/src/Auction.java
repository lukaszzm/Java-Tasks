/**
 * ServiceAuction interface
 *
 */
public interface Auction {

    /**
     * AuctionItem interface
     */
    interface AuctionItem {
        int getId();
        String getItemName();
        int getCurrentOffer();
        int getCurrentPrize();
    }

    /**
     * User notification when bid is outbid.
     */
    interface Notification {
        /**
         * Transmission of information about the outbidding of the observed auction item.
         *
         * @param item Object that represents current item of auction
         */
        void bidYourOffer(AuctionItem item);
    }

    /**
     * Method to add user to auction system
     *
     * @param username unique username
     * @param contact  object to inform user about auction
     */
    void addUser(String username, Notification contact);

    /**
     * Method to add item to auction system
     *
     * @param item item add to auction system
     */
    void addAuctionItem(AuctionItem item);

    /**
     * A user with the given username declares interest in an auction item with the given id.
     *
     * @param username username
     * @param auctionItemId id of auction item
     */
    void subscribe(String username, int auctionItemId);

    /**
     * The method completes the user's observation of the item in question.
     *
     * @param username username
     * @param auctionItemId id of auction item
     */
    void unsubscribe(String username, int auctionItemId);

    /**
     * The user submits an offer to purchase the item for the specified amount.
     *
     * @param username                      username
     * @param auctionItemId                 id of auction item
     * @param offeredPrize                  offeredPrize from user
     */
    void offer(String username, int auctionItemId, int offeredPrize);

    /**
     * Method end auction with given id.
     *
     * @param auctionItemId id of auction item
     */
    void endAuction(int auctionItemId);

    /**
     * The method allows you to know the name of the user
     * who offered the highest bid for the specified auction item.
     *
     * @param auctionItemId id of auction item
     * @return username of winner
     */
    String whoIsWinning(int auctionItemId);

    /**
     * The method allows you to find out the best offer for an item.
     *
     * @param auctionItemId id of auction item
     * @return highest offer for an item.
     */
    int highestOffer(int auctionItemId);

}
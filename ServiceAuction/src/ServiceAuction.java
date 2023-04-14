import java.util.*;

record Item(int id, String itemName, int currentOffer, int currentPrize) implements Auction.AuctionItem {
    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getItemName() { return this.itemName; }

    @Override
    public int getCurrentOffer() {
        return this.currentOffer;
    }

    @Override
    public int getCurrentPrize() {
        return this.currentPrize;
    }
}


public class ServiceAuction implements Auction {
    List<AuctionItem> items = new ArrayList<>();
    Map<String, Notification> users = new HashMap<>();
    Map<Integer, Map<String, Integer>> subscriptions = new HashMap<>();
    List<Integer> endedAuctions = new ArrayList<>();

    @Override
    public void addUser(String username, Notification contact) {
        users.put(username, contact);
    }

    @Override
    public void addAuctionItem(AuctionItem item) {
        items.add(item);
        Map<String, Integer> sub = new HashMap<>();
        subscriptions.put(item.getId(), sub);
    }

    @Override
    public void subscribe(String username, int auctionItemId) {
        if (subscriptions.containsKey(auctionItemId)) {
            Map<String, Integer> sub = subscriptions.get(auctionItemId);
            sub.put(username, -1);
        }
    }

    @Override
    public void unsubscribe(String username, int auctionItemId) {
        if (!subscriptions.containsKey(auctionItemId)) return;
        Map<String, Integer> sub = subscriptions.get(auctionItemId);
        sub.remove(username);
    }

    @Override
    public void offer(String username, int auctionItemId, int offeredPrize) {
        if (endedAuctions.contains(auctionItemId)) return;

        AuctionItem item = searchItem(auctionItemId);
        int index = items.indexOf(item);

        int highestPrize = item.getCurrentPrize();
        if (offeredPrize <= highestPrize) return;
        Map<String, Integer> sub = subscriptions.get(auctionItemId);
        if(sub.containsKey(username)) {
            sub.replace(username, offeredPrize);
        } else {
            sub.put(username, offeredPrize);
        }
        highestPrize = offeredPrize;
        AuctionItem newItem = new Item(auctionItemId, item.getItemName(), highestPrize, highestPrize);
        items.set(index, newItem);
        for (var key : sub.entrySet()) {
            if (!Objects.equals(key.getKey(), username)) {
                Notification notification = users.get(key.getKey());
                notification.bidYourOffer(newItem);
            }
        }

    }

    AuctionItem searchItem(int auctionItemId) {
        for (AuctionItem item : items) {
            if (item.getId() == auctionItemId) return item;
        }
        return null;
    }

    @Override
    public void endAuction(int auctionItemId) {
        endedAuctions.add(auctionItemId);
    }

    @Override
    public String whoIsWinning(int auctionItemId) {
        int highestOffer = highestOffer(auctionItemId);
        Map<String, Integer> sub = subscriptions.get(auctionItemId);
        for (var key : sub.entrySet()) {
            if (key.getValue() == highestOffer) {
                return key.getKey();
            }
        }
        return null;
    }

    @Override
    public int highestOffer(int auctionItemId) {
        AuctionItem item = searchItem(auctionItemId);
        return item.getCurrentPrize();
    }
}
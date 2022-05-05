package org.p2p.solanaj.core;

import org.junit.Test;
import org.p2p.solanaj.rpc.Cluster;
import org.p2p.solanaj.rpc.RpcClient;
import org.p2p.solanaj.rpc.RpcException;
import org.p2p.solanaj.rpc.types.CommitmentType;
import org.p2p.solanaj.rpc.types.TokenAccountsByOwner;
import org.p2p.solanaj.rpc.types.Transaction;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class GetTransactionTest {

    @Test
    public void testGettingTransaction() {

        final RpcClient client = new RpcClient(Cluster.DEVNET);

        try {
            final var transaction = client.getApi().getTransaction("2Nfeq923n2Ejxxtkn2vdTLmhFemcgjni5xaj6VSJHL7eocn9ALUTJNtK4Z2jV1mFmCDaSRcJzoZNFnojkA3ZXBz2", CommitmentType.FINALIZED);
            var accountKeys = transaction.getTransaction().getMessage().getAccountKeys();
            var key = accountKeys.indexOf("9tHxTM2cxAwEEEqyhYpqdAekNeVG2cny3PMRVQdbAQbV");
            assertEquals(2, key);
            var preBalance = transaction.getMeta().getPreBalances().get(key);
            var postBalance = transaction.getMeta().getPostBalances().get(key);
            var amount = postBalance - preBalance;
            var amountInSol = amount / Math.pow(10, 9);
            assertEquals(500000000, amount);
            assertEquals(0.5, amountInSol, 0);
        } catch (RpcException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGettingTransactionHTO() {

        final RpcClient client = new RpcClient(Cluster.DEVNET);

        try {
            final var transaction = client.getApi().getTransaction("4uHJG23hqbbKAtg8xxWxdToXYVF5d8qkExHRRN3KiNpPQcijJtETLXsnTroRjDa99bsNia9N2BeVRrqNTTuLy5GW", CommitmentType.FINALIZED);

            var balance = new Object() {
                BigDecimal preBalance;
                BigDecimal postBalance;
            };
            var mint = "htoHLBJV1err8xP5oxyQdV2PLQhtVjxLXpKB7FsgJQD";
            var owner = "9tHxTM2cxAwEEEqyhYpqdAekNeVG2cny3PMRVQdbAQbV";
            for (Transaction.Meta.TokenBalance preTokenBalance : transaction.getMeta().getPreTokenBalances()) {
                if (preTokenBalance.getOwner().equals(owner) && preTokenBalance.getMint().equals(mint)) {
                    balance.preBalance = new BigDecimal(preTokenBalance.getUiTokenAmount().getUiAmountString());
                    break;
                }
                balance.preBalance = BigDecimal.ZERO;
            }
            for (Transaction.Meta.TokenBalance postTokenBalance : transaction.getMeta().getPostTokenBalances()) {
                if (postTokenBalance.getOwner().equals(owner) && postTokenBalance.getMint().equals(mint)) {
                    balance.postBalance = new BigDecimal(postTokenBalance.getUiTokenAmount().getUiAmountString());
                    break;
                }
                balance.postBalance = BigDecimal.ZERO;
            }

            var amount = balance.postBalance.subtract(balance.preBalance);
            assertEquals(new BigDecimal("3.767"), amount);
        } catch (RpcException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetTokenArweaveJson() {

        final RpcClient client = new RpcClient(Cluster.MAINNET);

        try {
            var response = client.getApi().getAccountInfo(new PublicKey("BkSA63PGe7iGm7kUQj47HsgJWbx67QZo3ohBKrWNVgtU"));
            //var response = client.getApi().getTokenAccountsByOwner(new PublicKey("43TSUHmzZ3hLCuBinWiQKwfYTPYzWS1GDgw17wGiqcaA"), new PublicKey("BkSA63PGe7iGm7kUQj47HsgJWbx67QZo3ohBKrWNVgtU"), null);
            var v = response.getValue();
        } catch (RpcException e) {
            e.printStackTrace();
        }
    }
}

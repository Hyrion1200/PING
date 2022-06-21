package fr.epita.assistants.myide.domain.service;

import fr.epita.assistants.myide.domain.entity.Node;

public class NodeServ implements NodeService{
    @Override
    public Node update(Node node, int from, int to, byte[] insertedContent) {
        return null;
    }

    @Override
    public boolean delete(Node node) {
        return false;
    }

    @Override
    public Node create(Node folder, String name, Node.Type type) {
        return null;
    }

    @Override
    public Node move(Node nodeToMove, Node destinationFolder) {
        return null;
    }
}

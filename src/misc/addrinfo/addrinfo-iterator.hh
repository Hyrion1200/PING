/**
 * /file misc/addrinfo/addrinfo-iterator.hh
 * /brief Iterator over AddrInfo declaration.
 */
#pragma once

#include <iterator>
#include <netdb.h>

namespace misc
{
    /**
     * \class addrinfo_iterator
     * \brief Iterator over AddrInfo objects.
     */
    template <class Value,
              typename std::enable_if<
                  std::is_base_of<addrinfo, Value>::value>::type* = nullptr>
    class addrinfo_iter
        : public std::iterator<std::forward_iterator_tag, addrinfo_iter<Value>>
    {
    public:
        /**
         * \brief Default constructor (end iterator).
         */
        addrinfo_iter()
            : m_node{ nullptr }
        {}

        /**
         * \brief Constructor from a given AddrInfo.
         * \param p Value* the AddrInfo
         */
        explicit addrinfo_iter(Value* p)
            : m_node(p)
        {}

        /**
         * \brief Begin of the iterator range.
         */
        addrinfo_iter<Value> begin()
        {
            return *this;
        }

        /**
         * \brief End of the iterator range.
         */
        addrinfo_iter<Value> end()
        {
            return addrinfo_iter<Value>{};
        }

        /**
         * \brief Advance the iterator by one step.
         */
        addrinfo_iter<Value>& operator++()
        {
            m_node = m_node->ai_next;
            return *this;
        }

        addrinfo_iter<Value> operator++(int)
        {
            auto tmp = this;
            m_node = m_node->ai_next;
            return tmp;
        }

        /**
         * \brief Check equality between iterators.
         */
        bool operator==(const addrinfo_iter<Value>& other) const
        {
            return m_node == other.m_node;
        }

        bool operator!=(const addrinfo_iter<Value>& other) const
        {
            return m_node != other.m_node;
        }

        /**
         * \brief Access to the value through dereferenciation
         */
        Value& operator*() const
        {
            return *m_node;
        }

        Value* operator->() const
        {
            return m_node;
        }

    private:
        /**
         * \brief Current value of the iterator.
         */
        Value* m_node;
    };

    /**
     * \brief Alias to AddrInfo iterators type.
     */
    using addrinfo_iterator = addrinfo_iter<addrinfo>;
    /**
     * \brief Alias to const AddrInfo iterators type.
     */
    using addrinfo_const_iterator = addrinfo_iter<const addrinfo>;
} // namespace misc
